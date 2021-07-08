package basic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class Lambda2 implements RequestHandler<SQSEvent, String> {
    @Override
    public String handleRequest(SQSEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        StringBuilder response = new StringBuilder("lambda1 200 OK");
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            String bodyMsg = msg.getBody();
            response.append(bodyMsg);
        }
        logger.log(response.toString());
        return response.toString();
    }
}
