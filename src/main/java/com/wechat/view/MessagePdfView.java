package com.wechat.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wechat.pojo.ChatMessage;

public class MessagePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter pdfFile, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Map<Long,ChatMessage> chatHistory = (Map<Long, ChatMessage>) model.get("chatHistory");
		
		Table table = new Table(2);
		table.addCell("Message ID");
		table.addCell("Message Details");
		
		for(Long message: chatHistory.keySet()){
			String id = message.toString();
			String chatMessage = chatHistory.get(message).toString();
			table.addCell(id);
			table.addCell(chatMessage);
		}
		
		document.add(table);
	}

}
