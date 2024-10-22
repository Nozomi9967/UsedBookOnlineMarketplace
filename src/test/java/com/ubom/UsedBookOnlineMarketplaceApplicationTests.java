package com.ubom;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ubom.controller.BookController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UsedBookOnlineMarketplaceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() throws com.aliyuncs.exceptions.ClientException {
            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
            String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
            // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            // 填写Bucket名称，例如examplebucket。
            String bucketName = "web-ubom";
            // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
            String objectName = "1.jpg";
            // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
            // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            String filePath= "C:\\Users\\q1948\\Desktop\\UBOM\\res\\1.jpg";

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

            try {
                InputStream inputStream = new FileInputStream(filePath);
                // 创建PutObjectRequest对象。
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
                // 创建PutObject请求。
                PutObjectResult result = ossClient.putObject(putObjectRequest);
            } catch (OSSException oe) {
                System.out.println("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                System.out.println("Error Message:" + oe.getErrorMessage());
                System.out.println("Error Code:" + oe.getErrorCode());
                System.out.println("Request ID:" + oe.getRequestId());
                System.out.println("Host ID:" + oe.getHostId());
            } catch (ClientException | FileNotFoundException ce) {
                System.out.println("Caught an ClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with OSS, "
                        + "such as not being able to access the network.");
                System.out.println("Error Message:" + ce.getMessage());
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }

    }

    @Test
    public void JwtGenTest(){

        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","Nozomi");

        String jwt=Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"Nozomi")//签名算法
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))//有效期
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void JwtParseTest(){
        Claims claim = Jwts.parser()
                .setSigningKey("Nozomi")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzI5MDkxOTcwLCJ1c2VybmFtZSI6Ik5vem9taSJ9.5xhcEJZXl4RASLZLSGeQWachckS1xQHwytLGv6Nh3vg")
                .getBody();


        System.out.println(claim);
    }



    @Test
    public void testGetBean() {

        //根据bean的名称获取,bean名没指定则默认为类名，这里首字母小写
        BookController bean1 = (BookController) applicationContext.getBean("bookController");
        System.out.println(bean1);

        //根据bean的类型获取
        BookController bean2 = (BookController) applicationContext.getBean(BookController.class);
        System.out.println(bean2);

        //根据bean的名称和类型获取
        BookController bean3 = (BookController) applicationContext.getBean("bookController", BookController.class);
        System.out.println(bean3);
    }

}
