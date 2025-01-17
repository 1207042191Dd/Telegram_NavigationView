package com.example.telegram;

public class ChatItem {
    private String contactName;
    private String lastMessage;
    private String messageTime;
    private int unreadCount;

    public ChatItem(String contactName, String lastMessage, String messageTime, int unreadCount) {
        this.contactName = contactName;
        this.lastMessage = lastMessage;
        this.messageTime = messageTime;
        this.unreadCount = unreadCount;
    }

    public String getContactName() {
        return contactName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public int getUnreadCount() {
        return unreadCount;
    }
}