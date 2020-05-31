

[dubbo线程池](http://dubbo.apache.org/zh-cn/docs/user/demos/thread-model.html)
```xml
<dubbo:protocol name="dubbo" dispatcher="all" threadpool="fixed" threads="100" />
```

Dispatcher

    all 所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等。
    direct 所有消息都不派发到线程池，全部在 IO 线程上直接执行。
    message 只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
    execution 只请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
    connection 在 IO 线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池。

ThreadPool

    fixed 固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)
    cached 缓存线程池，空闲一分钟自动删除，需要时重建。
    limited 可伸缩线程池，但池中的线程数只会增长不会收缩。只增长不收缩的目的是为了避免收缩时突然来了大流量引起的性能问题。
    eager 优先创建Worker线程池。在任务数量大于corePoolSize但是小于maximumPoolSize时，优先创建Worker来处理任务。当任务数量大于maximumPoolSize时，将任务放入阻塞队列中。阻塞队列充满时抛出RejectedExecutionException。(相比于cached:cached在任务数量超过maximumPoolSize时直接抛出异常而不是将任务放入阻塞队列)


```XML
<dubbo:service interface="com.alibaba.dubbo.demo.DemoService" ref="demoService">
    <dubbo:parameter key="threadname" value="shuaiqi" />
    <dubbo:parameter key="threads" value="123" />
    <dubbo:parameter key="queues" value="10" />
</dubbo:service>
```