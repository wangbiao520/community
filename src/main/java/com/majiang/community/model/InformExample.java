package com.majiang.community.model;

import java.util.ArrayList;
import java.util.List;

public class InformExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public InformExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Long value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Long value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Long value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Long value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Long value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Long> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Long> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Long value1, Long value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Long value1, Long value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andInformUserIdIsNull() {
            addCriterion("inform_user_id is null");
            return (Criteria) this;
        }

        public Criteria andInformUserIdIsNotNull() {
            addCriterion("inform_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andInformUserIdEqualTo(Long value) {
            addCriterion("inform_user_id =", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdNotEqualTo(Long value) {
            addCriterion("inform_user_id <>", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdGreaterThan(Long value) {
            addCriterion("inform_user_id >", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("inform_user_id >=", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdLessThan(Long value) {
            addCriterion("inform_user_id <", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdLessThanOrEqualTo(Long value) {
            addCriterion("inform_user_id <=", value, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdIn(List<Long> values) {
            addCriterion("inform_user_id in", values, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdNotIn(List<Long> values) {
            addCriterion("inform_user_id not in", values, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdBetween(Long value1, Long value2) {
            addCriterion("inform_user_id between", value1, value2, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformUserIdNotBetween(Long value1, Long value2) {
            addCriterion("inform_user_id not between", value1, value2, "informUserId");
            return (Criteria) this;
        }

        public Criteria andInformIdIsNull() {
            addCriterion("inform_id is null");
            return (Criteria) this;
        }

        public Criteria andInformIdIsNotNull() {
            addCriterion("inform_id is not null");
            return (Criteria) this;
        }

        public Criteria andInformIdEqualTo(Long value) {
            addCriterion("inform_id =", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdNotEqualTo(Long value) {
            addCriterion("inform_id <>", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdGreaterThan(Long value) {
            addCriterion("inform_id >", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdGreaterThanOrEqualTo(Long value) {
            addCriterion("inform_id >=", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdLessThan(Long value) {
            addCriterion("inform_id <", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdLessThanOrEqualTo(Long value) {
            addCriterion("inform_id <=", value, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdIn(List<Long> values) {
            addCriterion("inform_id in", values, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdNotIn(List<Long> values) {
            addCriterion("inform_id not in", values, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdBetween(Long value1, Long value2) {
            addCriterion("inform_id between", value1, value2, "informId");
            return (Criteria) this;
        }

        public Criteria andInformIdNotBetween(Long value1, Long value2) {
            addCriterion("inform_id not between", value1, value2, "informId");
            return (Criteria) this;
        }

        public Criteria andInformTitleIsNull() {
            addCriterion("inform_title is null");
            return (Criteria) this;
        }

        public Criteria andInformTitleIsNotNull() {
            addCriterion("inform_title is not null");
            return (Criteria) this;
        }

        public Criteria andInformTitleEqualTo(String value) {
            addCriterion("inform_title =", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleNotEqualTo(String value) {
            addCriterion("inform_title <>", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleGreaterThan(String value) {
            addCriterion("inform_title >", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleGreaterThanOrEqualTo(String value) {
            addCriterion("inform_title >=", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleLessThan(String value) {
            addCriterion("inform_title <", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleLessThanOrEqualTo(String value) {
            addCriterion("inform_title <=", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleLike(String value) {
            addCriterion("inform_title like", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleNotLike(String value) {
            addCriterion("inform_title not like", value, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleIn(List<String> values) {
            addCriterion("inform_title in", values, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleNotIn(List<String> values) {
            addCriterion("inform_title not in", values, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleBetween(String value1, String value2) {
            addCriterion("inform_title between", value1, value2, "informTitle");
            return (Criteria) this;
        }

        public Criteria andInformTitleNotBetween(String value1, String value2) {
            addCriterion("inform_title not between", value1, value2, "informTitle");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdIsNull() {
            addCriterion("begin_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdIsNotNull() {
            addCriterion("begin_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdEqualTo(Long value) {
            addCriterion("begin_user_id =", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdNotEqualTo(Long value) {
            addCriterion("begin_user_id <>", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdGreaterThan(Long value) {
            addCriterion("begin_user_id >", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("begin_user_id >=", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdLessThan(Long value) {
            addCriterion("begin_user_id <", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdLessThanOrEqualTo(Long value) {
            addCriterion("begin_user_id <=", value, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdIn(List<Long> values) {
            addCriterion("begin_user_id in", values, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdNotIn(List<Long> values) {
            addCriterion("begin_user_id not in", values, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdBetween(Long value1, Long value2) {
            addCriterion("begin_user_id between", value1, value2, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserIdNotBetween(Long value1, Long value2) {
            addCriterion("begin_user_id not between", value1, value2, "beginUserId");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameIsNull() {
            addCriterion("begin_user_name is null");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameIsNotNull() {
            addCriterion("begin_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameEqualTo(String value) {
            addCriterion("begin_user_name =", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameNotEqualTo(String value) {
            addCriterion("begin_user_name <>", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameGreaterThan(String value) {
            addCriterion("begin_user_name >", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("begin_user_name >=", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameLessThan(String value) {
            addCriterion("begin_user_name <", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameLessThanOrEqualTo(String value) {
            addCriterion("begin_user_name <=", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameLike(String value) {
            addCriterion("begin_user_name like", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameNotLike(String value) {
            addCriterion("begin_user_name not like", value, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameIn(List<String> values) {
            addCriterion("begin_user_name in", values, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameNotIn(List<String> values) {
            addCriterion("begin_user_name not in", values, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameBetween(String value1, String value2) {
            addCriterion("begin_user_name between", value1, value2, "beginUserName");
            return (Criteria) this;
        }

        public Criteria andBeginUserNameNotBetween(String value1, String value2) {
            addCriterion("begin_user_name not between", value1, value2, "beginUserName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inform
     *
     * @mbg.generated do_not_delete_during_merge Mon Sep 16 13:08:20 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inform
     *
     * @mbg.generated Mon Sep 16 13:08:20 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}