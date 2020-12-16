package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException{
		
		//업로드 확인...
		//소켓 생성
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=================================");
		System.out.println("[서버에 연결을 요청합니다.]");
		
		
		socket.connect(new InetSocketAddress("192.168.219.101", 10001));
		
		
		System.out.println("서버에 연결되었습니다.");
		//socket <--> socket 종이컵 전화기
		//메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너
		Scanner sc = new Scanner(System.in);
		
		//반복구간
		while(true) { //디버깅 --> 내가 생각한 횟수대로 반복이 되고 있는지 확인
			
			String str = sc.nextLine();
			
			if(str.equals("/q")) {  //str /q같으면 종료
				break;
			}

			//메세지 보내기
			bw.write(str); // Scanner로 입력받은 값 보내기
			bw.newLine();
			bw.flush(); // 크기가 충분하지 않아도 일단 전송하라는 의미
	
			
			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("setver:[" + reMsg + "]");
		
		}
		
		//지원종료
		bw.close();
		br.close();
		
		System.out.println("=====================");
		System.out.println("<클라이언트 종료>");
		socket.close();
		sc.close();
		
		
		
	}

}
