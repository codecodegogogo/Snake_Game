import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon body=new ImageIcon("src/snake.png");
    ImageIcon food=new ImageIcon("src/food.png");
//    蛇的长度
    int length=2;
//    总分
    int score=0;
//    游戏是否开始
    boolean isStart=false;
//    移动方向
    Direction direction=Direction.right;
//    游戏状态
    State state=State.ing;
//    定时器刷新页面
    Timer timer=new Timer(200,this);
//    用蛇最多块数确定位置  width/25px
    int[] x=new int[396];
    int[] y=new int[396];
    int foodx;
    int foody;
    Random random=new Random();
    public MyPanel() {
        // 设置面板的大小
        setPreferredSize(new Dimension(450, 550));
        x[0]=50;
        x[1]=25;
        y[0]=50;
        y[1]=50;
        setFocusable(true);
        addKeyListener(this);
        timer.start();
        foodx=25+25*random.nextInt(15);
        foody=25+25*random.nextInt(18);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.RED);
        // 绘制其他图形或添加组件的逻辑可以在这里添加
        g.fillRect(0,0,450,530);
        switch (direction){
            case up:
                body.paintIcon(this,g,x[0],y[0]);
                break;
            case bottom:
                body.paintIcon(this,g,x[0],y[0]);
                break;
            case right:
                body.paintIcon(this,g,x[0],y[0]);
                break;
            case left:
                body.paintIcon(this,g,x[0],y[0]);
                break;
        }
        for (int i=1;i<length;i++){
            body.paintIcon(this,g,x[i],y[i]);
        }
        g.setColor(Color.white);
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.drawString("score:"+score,25,25);
        if(!isStart){
            timer.stop();
            g.drawString("按空格开始",170,400);
        }else {
            timer.start();
        }
        switch (state){
            case victory:
                g.drawString("victory!", 170, 450);
                timer.stop();
                isStart=!isStart;
                break;
            case fail:
                g.drawString("Game Over!", 170, 450);
                isStart=!isStart;
                timer.stop();
                break;
            case ing:
                break;
        }
        if(x[0]==foodx && y[0]==foody) {
            length++;
            score++;
            foodx = 25 + 25 * random.nextInt(15);
            foody = 25 + 25 * random.nextInt(18);
            g.drawString("score:"+score,25,25);
        }
        food.paintIcon(this,g,foodx,foody);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    private void resetGame() {
        // 重新初始化游戏状态
        length = 2;
        score = 0;
        isStart = false;
        direction = Direction.right;
        state = State.ing;
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if (code==32){
            if (state == State.fail || state == State.victory) {
                // 重新开始游戏
                resetGame();
            }else {
                isStart = !isStart;
                repaint();
            }
        } else if (code== KeyEvent.VK_UP && direction!=Direction.bottom) {
            direction=Direction.up;
        } else if (code==KeyEvent.VK_DOWN && direction!=Direction.up) {
            direction=Direction.bottom;
        } else if (code==KeyEvent.VK_LEFT && direction!=Direction.right) {
            direction=Direction.left;
        } else if (code==KeyEvent.VK_RIGHT && direction!=Direction.left) {
            direction=Direction.right;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x[0]==foodx && y[0]==foody){
            System.out.println("food刷新");
            repaint();
        }
        if(score==x.length){
            state=State.victory;
        }
        for (int i=length-1;i>0;i--){
            if (x[0]==x[i] && y[0]==y[i]){
                state=State.fail;
            }
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction){
            case up:
                y[0]-=25;
                if(y[0]<0){
                    y[0]=525;
                }
                break;
            case bottom:
                y[0]+=25;
                if(y[0]>525){
                    y[0]=0;
                }
                break;
            case left:
                x[0]-=25;
                if(x[0]<0){
                    x[0]=425;
                }
                break;
            case right:
                x[0]+=25;
                if(x[0]>425){
                    x[0]=0;
                }
                break;
        }
        repaint();
        timer.start();
    }
}
