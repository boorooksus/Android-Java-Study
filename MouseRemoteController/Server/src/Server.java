import java.awt.*;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;

public class Server {

    static boolean mRun = true;
    static IpAddress ipAddress = new IpAddress();

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(ipAddress.getIpAddress(), 5000));
        System.out.println("Server Start!");

        while(mRun) {
            Socket socket = server.accept();
            System.out.println("Connected...");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            int NetworkType = dis.readInt();;

            Robot bot = new Robot();

            if (NetworkType == 0){
                // 커서 움직임
                int x = dis.readInt();
                int y = dis.readInt();

                bot.mouseMove(x, y);

            } else{
                // 마우스 클릭

                int clickedButton = dis.readInt();

                System.out.println("Clicked Button: " + clickedButton);

                switch (clickedButton){
                    case 1:
                        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(10);
                        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;

                    case 3:
                        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        Thread.sleep(10);
                        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                        break;

                    default:

                }

//                if (isLeftClick && !isRightClick){
//                    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//                }
//                else if (!isLeftClick && !isRightClick){
//                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//                }
//                else if (!isLeftClick && isRightClick){
//                    bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//                    Thread.sleep(10);
//                    bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//                }
//                else{
//                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//                    bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//                }

            }

            //bot.mouseMove(0, 0);
            socket.close();
        }

        server.close();
        System.out.println("Terminated...");
    }
}
