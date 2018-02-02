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
		service.setUsernameAndPassword("0896e7f5-0056-4df7-beba-0b15d38673c0", "Gl36m0vLZ58v");
		MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context).build();
		String workspaceId = "a537f788-0f39-4d13-abd4-f503545fa073";
		MessageResponse response = service.message(workspaceId, newMessage).execute();
		return response;
	}
}
