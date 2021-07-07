package basic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class Lambda1 implements RequestHandler<SNSEvent, String> {
    @Override
    public String handleRequest(SNSEvent event, Context context) {
        //print log to cloud watch
        LambdaLogger logger = context.getLogger();
        StringBuilder response = new StringBuilder("lambda1 200 OK");
        for (SNSEvent.SNSRecord msg : event.getRecords()) {
            response.append(msg);
        }
        logger.log(response.toString());
        return response.toString();
    }
}
