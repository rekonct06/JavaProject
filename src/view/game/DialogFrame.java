import java.awt.Container;// 容器
import java.awt.FlowLayout;// 布局
import java.awt.event.ActionEvent;// 动作事件
import java.awt.event.ActionListener; // 动作监听
import javax.swing.*;// JDialog窗体是Swing组件中的对话框

class DialogFrame extends JDialog{		// Demo继承JDialog类
    public DialogFrame(JFrame frame){
        /*
         * 第一个参数：父窗体对象
         * 第二个参数：对话框标题
         * 第三个参数：是否阻塞父窗体
         */
        super(frame,"对话框标题",true);

        Container c=getContentPane();// 获取窗体容器，Container（容器），content（内容），pane（窗格）
        c.add(new JLabel("这是一个对话框"));// 设置一个标签，label（标签）

//1		setVisible(true);// 设置窗体可见，visible（可见物），放这最后对话框缩起来
        setBounds(400,300,100,100);// 设置窗体坐标，x、y、长、宽，bounds（界限）
    }

    public static void main(String[] args){
        JFrame f=new JFrame("父窗体");
        f.setBounds(300,200,300,300);
        Container c=f.getContentPane();
        JButton btn=new JButton("弹出对话框");
        c.setLayout(new FlowLayout());//设置布局，使用流布局
        c.add(btn);// 容器c里添加按钮
        f.setVisible(true);// 窗体是否可见
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);// 关闭规则

        //添加动作监听
        btn.addActionListener(new ActionListener(){	// Action（行动）
            public void actionPerformed(ActionEvent e){// performed（执行），event（事件）
//1				new Demo(f);// 传入f
                // 设置Demo类d，对d进行操作指令
                DialogFrame d=new DialogFrame(f);
                d.setVisible(true);// 设置窗体可视化
            }
        });
    }
}
