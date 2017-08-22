package cn.didano.weichat.aliyun;

public interface DegradationFilter {
    boolean shouldDegradeHttpDNS(String hostName);
}
