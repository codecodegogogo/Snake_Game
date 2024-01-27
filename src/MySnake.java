import javax.swing.*;
import java.awt.*;

public class MySnake {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(100, 100, 460, 561);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置布局管理器为 BorderLayout
        jFrame.setLayout(new BorderLayout());

        // 添加 MyPanel 到 JFrame 的中央位置
        jFrame.add(new MyPanel(), BorderLayout.CENTER);

        jFrame.setVisible(true);
    }
}
