package com.selfdevelopement.assignment.api;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

public class QueueAPI {

    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=csg10032000d1fbbcb3;AccountKey=YpaiST5djZsxhUuaZ+ZGo/lu3hoesLH8PheBQJrLdv9V0ptfvMYtj/2IH184472XaAwUEmACtVp6wkdArCRGeQ==";
    String queuename = "rash";


    private CloudQueue getQueueReference() throws Exception {

        CloudStorageAccount storageAccount =
                CloudStorageAccount.parse(storageConnectionString);

        // Create the queue client.
        CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

        // Retrieve a reference to a queue.
        return queueClient.getQueueReference(queuename);
    }


    public long getlength() {
        try {
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the queue client.
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            // Retrieve a reference to a queue.
            CloudQueue queue = queueClient.getQueueReference(queuename);

            queue.downloadAttributes();

            // Retrieve the newly cached approximate message count.
            return queue.getApproximateMessageCount();
        } catch (Exception e) {

        }
        return 0;
    }


    public Boolean deQueue() {
        boolean status = true;
        try
        {
            // Retrieve a reference to a queue.
            CloudQueue queue = getQueueReference();

            // Retrieve the first visible message in the queue.
            CloudQueueMessage retrievedMessage = queue.retrieveMessage();

            if (retrievedMessage != null)
            {
                // Process the message in less than 30 seconds, and then delete the message.
                queue.deleteMessage(retrievedMessage);
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            status = false;
            e.printStackTrace();
        }
return status;
    }



    public String peekMessage() {
        String msg = null;
        try
        {
            // Retrieve a reference to a queue.
            CloudQueue queue = getQueueReference();

            // Peek at the next message.
            CloudQueueMessage peekedMessage = queue.peekMessage();

            // Output the message value.

            if (peekedMessage != null)
            {
                msg = peekedMessage.getMessageContentAsString();
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return msg;
    }

    public boolean addMessageToQueue(String message) {
        boolean status = true;
        try
        {
            CloudQueue queue = getQueueReference();

            // Create the queue if it doesn't already exist.
            queue.createIfNotExists();

            // Create a message and add it to the queue.
            CloudQueueMessage msg = new CloudQueueMessage(message);
            queue.addMessage(msg);
        }
        catch (Exception e)
        {
            // Output the stack trace.
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    public String getQueueDetails() {
        try
        {
            CloudQueue queue = getQueueReference();

            // Create the queue if it doesn't already exist.
            queue.createIfNotExists();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "ras";
    }

    public static void main(String args[]) {
        QueueAPI queueAPI = new QueueAPI();
        queueAPI.getQueueDetails();
    }


    public String createQueueIfDoesNotExist() {
        try
        {
            CloudQueue queue = getQueueReference();

            // Create the queue if it doesn't already exist.
            queue.createIfNotExists();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "ras";
    }
}