package com.majiang.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class UCloudProvider {


    @Value("${ucloud.ufile.public-key}")
    private String publicKey;
    @Value("${ucloud.ufile.private-key}")
    private String privateKey;
    @Value("${ucloud.ufile.bucketName}")
    private String bucketName;
    @Value("${ucloud.ufile.expires}")
    private Integer expires;
    @Value("${ucloud.ufile.proxy-suffix}")
    private String proxySuffix;
    @Value("${ucloud.ufile.region}")
    private String region;

    public String upload(InputStream inputStream, String mimeType,String fileName){
        ObjectAuthorization bucketAuthorizer = new UfileObjectLocalAuthorization(publicKey,privateKey);
        ObjectConfig config = new ObjectConfig(region, proxySuffix);

        String[] split = fileName.split("\\.");
        if(split.length > 1){
            fileName = UUID.randomUUID().toString() + "." + split[split.length - 1];
        }else{
            return null;
        }
        try {
            PutObjectResultBean response = UfileClient.object(bucketAuthorizer, config)
                    .putObject(inputStream, mimeType)
                    .nameAs(fileName)
                    .toBucket(bucketName)
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();

            if(response != null && response.getRetCode() == 0){
                String url = UfileClient.object(bucketAuthorizer, config)
                        .getDownloadUrlFromPrivateBucket(fileName, bucketName, expires)
                        .createUrl();
                return url;
            }else{
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (UfileClientException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        } catch (UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }



}
