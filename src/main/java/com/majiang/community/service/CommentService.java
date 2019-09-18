package com.majiang.community.service;

import com.majiang.community.dto.CommentDTO;
import com.majiang.community.enums.CommentTypeEnum;
import com.majiang.community.enums.InformStatusEnum;
import com.majiang.community.enums.InformTypeEnum;
import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import com.majiang.community.mapper.*;
import com.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {


    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private InformMapper informMapper;

    @Transactional
    public void insert(Comment comment,User commentUser) {

        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw  new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_NOT_FOUND);
        }

        Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
        Question dbQuestion = new Question();
        if(dbComment != null){
            dbQuestion = questionMapper.selectByPrimaryKey(dbComment.getParentId());
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){

            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //新增评论
            commentMapper.insert(comment);

            //新增评论通知
            insertInform(comment, commentUser, dbQuestion, dbQuestion.getId(),dbComment.getCommentator(), InformStatusEnum.UNREAD,InformTypeEnum.COMMENT);

            //新增评论数
            Comment upComment = new Comment();
            upComment.setId(comment.getParentId());
            upComment.setCommentCount(1L);
            commentExtMapper.inCommentCount(upComment);

        }else{
            dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(dbQuestion == null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //新增回复
            comment.setCommentCount(0L);
            commentMapper.insert(comment);

            //新增回复通知
            insertInform(comment,commentUser,dbQuestion,dbQuestion.getId(),dbQuestion.getCreator(),InformStatusEnum.UNREAD,InformTypeEnum.REPLY);

            //新增回复数
            dbQuestion.setCommentCount(1);
            questionExtMapper.inCommentCount(dbQuestion);
        }

    }

    private void insertInform(Comment comment, User commentUser, Question dbQuestion,Long id,Long userId, InformStatusEnum unread, InformTypeEnum reply) {
        Inform inform = new Inform();
        inform.setInformId(id);
        inform.setInformUserId(userId);
        inform.setBeginUserId(comment.getCommentator());
        inform.setBeginUserName(commentUser.getName());
        inform.setGmtCreate(System.currentTimeMillis());
        inform.setStatus(unread.getStatus());
        inform.setType(reply.getType());
        inform.setInformTitle(dbQuestion.getTitle());
        informMapper.insert(inform);

    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {

        //拿到comments
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        //拿到userIds 转换为Map
        if(comments == null || comments.size() == 0){
            return new ArrayList<>();
        }
        Set<Long> collect = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = collect.stream().collect(Collectors.toList());
        UserExample example = new UserExample();
        example.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(example);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //将commengs 转换为 commentDTOs
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
