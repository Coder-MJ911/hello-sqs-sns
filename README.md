# AWS SelfLearning

# Description

this is just for AWS Learning about Lambda | SNS | SQS | CloudWatch

Basic:

work flow:
![image](https://github.com/Ma-Jiajie/AnyPictures/blob/master/sqsSnsBasic.png)

```html
Event schedule 定时触发 SNS，SNS 收到消息后会发邮件，触发Lambda,也会将消息发送到 SQS 中，SQS 收到消息后触发 Lambda
ACs
每 5 mins 自动触发SNS的Topic
SNS 自动发送邮件，邮件内容包含所有 event 的内容
SNS 会触发Lambda，Lambda将event的所有内容输出到CloudWatch log中
SNS 向订阅的 SQS中发送消息
SQS要有retention(7days) / timeout(5mins)
SQS自动触发Lambda运行，Lambda将event的所有内容输出到CloudWatch log中
```

# How to deploy basic stack
```shell
$ saml2aws login --profile tw-aws-beach
$ sls deploy -v
```



Advance:
![image](https://github.com/Ma-Jiajie/AnyPictures/blob/master/sqsSnsAdvance.png)

```html
ACs
有可部署 Basic 中内容的 Cloudformation
对比SNS->Lambda和SNS->SQS->Lambda的区别
同时对于 SQS和Lambda 要有 DLQ(dead letter queue)
使用 IAM 控制 访问权限
为 SNS 添加 tag（eg. Project code, owner)
使用KMS对SQS/SNS进行加密
使用cloudwatch监控DLQ,在DLQ中有消息时发送通知邮件
为SNS中的message部分添加MessageAttributes,并为Subscription增加过滤条件，每个consumer只接收部分消息(可以使用CLI或者Console来发送消息,进行验证)
```