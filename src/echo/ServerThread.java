package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	
	//메소드 g/s
	
	//메소드 일반
	
	@Override
	public void run() {
		
		try {
			
			// 서버 클래스 실행하고 --> cmd 가서 클라이언트 클래스 실행
			//메시지 받기용 스트림
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			
			//메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			//반복구간
			while(true) {
				//메세지 받기
				String msg = br.readLine();

				if(msg == null) {
					break;
				}
				
				System.out.println("받은메세지:" + msg);
				
				//메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			
			}
			
			br.close();
			bw.close();
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
