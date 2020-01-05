APIM对于其他组最大的问题是他们在一接手的时候不知道这个东西是什么？能干什么？怎么去用？和谁联系？如果他们选择我们，那么一定是我们能够帮助他们节省时间并且提供他们想要的功能。那么根据MT的经验，如果我们想要提供一个成熟的并且有竞争力的公用反向代理我们需要做到以下功能：
1. Load Balance, 并且能供提供多种负载均衡策略。
2. 服务器健康检查，能够及时识别到出现问题的后台服务器并进行熔断处理，当服务器恢复工作时能重新将其加入服务集群。
3. 提供QueryParameter映射功能，如果有对应需求我们可以提供ParameterName映射以及ParameterValue的映射。  
	EX. {"reportId":"116411"}  -> {"Report Name":"Sample Report"}
4. Entitlement Check，集合CPS来提供授权检查，来判断Client是否有权限访问相应的服务API。（From my perspective, this will be the most complex part. We need to persuade CPS to add APIM ClientId in their system.）
5. XSSFilter，提供基础的XSS Check包含过滤掉如下字符。并且同时提供针对指定的query parameter进行正则检查。
6. 基于Hystrix提供服务的熔断及限流功能，并可以根据用户需求提供定制化限流策略。
7. Logs，记录每个request的信息如是否发送成功，返回消耗时间等等， 以便于日后分析使用。
8. 提供一个详细的说明书以及模板，以便于其他组可以快速方便的与我们的系统以及APIM进行集合。