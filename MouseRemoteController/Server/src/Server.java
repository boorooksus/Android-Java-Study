import java.awt.*;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static boolean mRun = true;
    // ip address 정보 객체 생성
    static IpAddress ipAddress = new IpAddress();
    static PointerInfo pt = MouseInfo.getPointerInfo();

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(ipAddress.getIpAddress(), 5000));
        System.out.println("Server Start!");

        // 마지막으로 전송 받은 터치 위치 좌표
        int lastX = 0;
        int lastY = 0;
        // 현재 서버 컴퓨터의 마우스 위치 좌표
        int curX = (int)pt.getLocation().x;
        int curY = (int)pt.getLocation().y;

        while(mRun) {
            Socket socket = server.accept();
            System.out.println("Connected...");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // 받는 내용 타입.
            // 0: 커서 움직임 정보, 1: 클릭 정보
            int NetworkType = dis.readInt();

            Robot bot = new Robot();

            if (NetworkType == 0){
                // 커서 움직이는 경우

                int x = dis.readInt();
                int y = dis.readInt();

                // 스마트폰에서 화면을 드래그 할 때만 마우스 커서를 이동시키도록 함
                if(Math.abs(lastX - x) < 100 && Math.abs(lastY - y) < 100){
                    curX += x - lastX;
                    curY += y - lastY;

                    bot.mouseMove(curX, curY);
                }
                lastX = x;
                lastY = y;

            } else{
                // 마우스 클릭인 경우

                int clickedButton = dis.readInt();

                System.out.println("Clicked Button: " + clickedButton);

                switch (clickedButton){
                    case 1:
                        // 좌클릭
                        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(10);
                        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;

                    case 3:
                        // 우클릭
                        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        Thread.sleep(10);
                        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                        break;

                    default:

                }
            }
            socket.close();
        }
        server.close();
        System.out.println("Terminated...");
    }
}
