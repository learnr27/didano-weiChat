package cn.didano.weichat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

import cn.didano.weichat.config.OssInfo;

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 转换文件路径
	 * @param modulename
	 * @param inputStream
	 * @return
	 */
	public  static void transferTempUrl2FormalUrlWithAliOss(String content,OssInfo ossInfo){
		//获取所有图片src
		List<String> imglist= getImgSrc(content);
		//判断是否是阿里的资源
		for(String httpurl:imglist){
			int location = httpurl.indexOf(ossInfo.getTmpPath());
			int tmpPathLength = ossInfo.getTmpPath().length();
			if(location!=-1){
				//http://image-didanuo.oss-cn-shenzhen.aliyuncs.com/tmp/
				//http://image-didanuo.oss-cn-shenzhen.aliyuncs.com/formal/
				//阿里的文件资源路径
				String sourceKey = httpurl.substring(tmpPathLength);
				//要拷贝到的阿里的文件资源路径
				String destinationKey = sourceKey.replaceFirst(ossInfo.getTmp(), ossInfo.getFormal());
				OSSClient ossClient = new OSSClient(ossInfo.getEndpoint(), ossInfo.getAccessKeyId(), ossInfo.getAccessKeySecret());
				ossClient.copyObject(ossInfo.getBucketname(), sourceKey, ossInfo.getBucketname(), destinationKey);
				ossClient.shutdown();
				//用新的url替代老的url
				content.replaceAll(ossInfo.getTmpPath(), ossInfo.getFormalPath());
			}
		}
	}
	

	
	/*
	 * 获取img标签中的src值
     * @param content
     * @return
     */
    public static List<String> getImgSrc(String content){
        List<String> list = new ArrayList<String>();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);
                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                //结束匹配<img />标签中的src

                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }
	
    public static  void main(String[] ss){
    	String aa = "http://a.b.com/tmp/b/c.jpg";
    	String bb = "http://a.b.com/tmp/";
    	int location = aa.indexOf("http://a.b.com/tmp/");
    	String get = aa.substring(bb.length());
    	System.out.println("get="+get);
    }
}
