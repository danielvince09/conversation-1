package com.acn.acit.watsonservices.conversation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		MessageResponse response = null;
		Map context = new HashMap();

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print("Say Something : ");
				String input = br.readLine();
				if ("q".equals(input)) {
					System.out.println("Exit!");
					System.exit(0);
				}
				response = conversationAPI(input, context);
				String output = response.getText().get(0);
				context = response.getContext();
				System.out.println("Watson says: " + output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MessageResponse conversationAPI(String input, Map context) {
		ConversationService service = new ConversationService("2017-02-03");
		service.setUsernameAndPassword("979066d9-b1f2-426a-a9d5-ecb96a7ae896", "onfWtATZL4ZU");
		MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context).build();
		String workspaceId = "0accd695-8e90-4ad0-bcc4-cd667c8d93bc";
		MessageResponse response = service.message(workspaceId, newMessage).execute();
		return response;
	}
}
