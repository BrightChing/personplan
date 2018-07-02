package io.github.brightqin.personplan.util;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 日期选择器，可以指定日期的显示格式
 */
public class DateChooser extends JPanel {

    private static final long serialVersionUID = 4529266044762990227L;
    private final LabelManager lm = new LabelManager();
    private Date initDate;
    private Calendar now = Calendar.getInstance();
    private Calendar select;
    private JPanel monthPanel;//月历
    private JP1 jp1;//四块面板,组成
    private JP2 jp2;
    private JP3 jp3;
    private JP4 jp4;
    private Font font = new Font("宋体", Font.PLAIN, 12);
    private SimpleDateFormat sdf;
    private boolean isShow = false;
    private Popup pop;

    private JComponent showDate;

    /**
     * Creates a new instance of DateChooser
     */
    private DateChooser() {
        this(new Date());
    }

    private DateChooser(Date date) {
        this(date, "yyyy年MM月dd日");
    }

    private DateChooser(String format) {
        this(new Date(), format);
    }

    private DateChooser(Date date, String format) {
        initDate = date;
        sdf = new SimpleDateFormat(format);
        select = Calendar.getInstance();
        select.setTime(initDate);
        initPanel();
    }

    public static DateChooser getInstance() {
        return new DateChooser();
    }

    public static DateChooser getInstance(Date date) {
        return new DateChooser(date);
    }

    public static DateChooser getInstance(String format) {
        return new DateChooser(format);
    }

    public static DateChooser getInstance(Date date, String format) {
        return new DateChooser(date, format);
    }

    public static void main(String[] args) {
        DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
        DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
        JTextField showDate1 = new JTextField("单击选择日期");
        JLabel showDate2 = new JLabel("单击选择日期");

        dateChooser1.register(showDate1);
        dateChooser2.register(showDate2);

        JFrame jf = new JFrame("测试日期选择器");
        jf.add(showDate1, BorderLayout.NORTH);
        jf.add(showDate2, BorderLayout.SOUTH);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 是否允许用户选择
     */
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        showDate.setEnabled(b);
    }

    /**
     * 得到当前选择框的日期
     */
    public Date getDate() {
        return select.getTime();
    }

    public String getStrDate() {
        return sdf.format(select.getTime());
    }

    public String getStrDate(String format) {
        sdf = new SimpleDateFormat(format);
        return sdf.format(select.getTime());
    }

    //根据初始化的日期,初始化面板
    private void initPanel() {
        monthPanel = new JPanel(new BorderLayout());
        monthPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel up = new JPanel(new BorderLayout());
        up.add(jp1 = new JP1(), BorderLayout.NORTH);
        up.add(jp2 = new JP2(), BorderLayout.CENTER);
        monthPanel.add(jp3 = new JP3(), BorderLayout.CENTER);
        monthPanel.add(up, BorderLayout.NORTH);
        monthPanel.add(jp4 = new JP4(), BorderLayout.SOUTH);
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {

            }

            public void ancestorRemoved(AncestorEvent event) {

            }

            //只要祖先组件一移动,马上就让popup消失
            public void ancestorMoved(AncestorEvent event) {
                hidePanel();
            }
        });
    }

    public void register(final JComponent showDate) {
        this.showDate = showDate;

        showDate.setRequestFocusEnabled(true);
        showDate.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                showDate.requestFocusInWindow();
            }
        });
        this.setBackground(Color.WHITE);
        this.add(showDate, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(90, 25));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        showDate.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    showDate.setForeground(Color.RED);
                }
            }

            public void mouseExited(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    showDate.setForeground(Color.BLACK);
                }
            }

            public void mousePressed(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setForeground(Color.CYAN);
                    if (isShow) {
                        hidePanel();
                    } else {
                        showPanel(showDate);
                    }
                }
            }

            public void mouseReleased(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setForeground(Color.BLACK);
                }
            }
        });
        showDate.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                hidePanel();
            }

            public void focusGained(FocusEvent e) {

            }
        });
    }

    //根据新的日期刷新
    private void refresh() {
        jp1.updateDate();
        jp2.updateDate();
        jp3.updateDate();
        jp4.updateDate();
        SwingUtilities.updateComponentTreeUI(this);
    }

    //提交日期
    private void commit() {
        //TODO add other components here
        if (showDate instanceof JTextField) {
            ((JTextField) showDate).setText(sdf.format(select.getTime()));
        } else if (showDate instanceof JLabel) {
            ((JLabel) showDate).setText(sdf.format(select.getTime()));
        }

        hidePanel();
    }

    //隐藏日期选择面板
    private void hidePanel() {
        if (pop != null) {
            isShow = false;
            pop.hide();
            pop = null;
        }
    }

    //显示日期选择面板
    private void showPanel(Component owner) {
        if (pop != null) {
            pop.hide();
        }
        Point show = new Point(0, showDate.getHeight());
        SwingUtilities.convertPointToScreen(show, showDate);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = show.x;
        int y = show.y;
        if (x < 0) {
            x = 0;
        }
        if (x > size.width - 295) {
            x = size.width - 295;
        }
        if (y < size.height - 170) {
        } else {
            y -= 188;
        }
        pop = PopupFactory.getSharedInstance().getPopup(owner, monthPanel, x, y);
        pop.show();
        isShow = true;
    }

    /**
     * 最上面的面板用来显示月份的增减
     */
    private class JP1 extends JPanel {
        private static final long serialVersionUID = -5638853772805561174L;
        JLabel yearLeft, yearRight, monthLeft, monthRight, center, centerContainer;

        public JP1() {
            super(new BorderLayout());
            this.setBackground(new Color(160, 185, 215));
            initJP1();
        }

        private void initJP1() {
            yearLeft = new JLabel("  <<", JLabel.CENTER);
            yearLeft.setToolTipText("上一年");
            yearRight = new JLabel(">>  ", JLabel.CENTER);
            yearRight.setToolTipText("下一年");
            yearLeft.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
            yearRight.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

            monthLeft = new JLabel("  <", JLabel.RIGHT);
            monthLeft.setToolTipText("上一月");
            monthRight = new JLabel(">  ", JLabel.LEFT);
            monthRight.setToolTipText("下一月");
            monthLeft.setBorder(BorderFactory.createEmptyBorder(2, 30, 0, 0));
            monthRight.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 30));

            centerContainer = new JLabel("", JLabel.CENTER);
            centerContainer.setLayout(new BorderLayout());
            center = new JLabel("", JLabel.CENTER);

            centerContainer.add(monthLeft, BorderLayout.WEST);
            centerContainer.add(center, BorderLayout.CENTER);
            centerContainer.add(monthRight, BorderLayout.EAST);

            this.add(yearLeft, BorderLayout.WEST);
            this.add(centerContainer, BorderLayout.CENTER);
            this.add(yearRight, BorderLayout.EAST);
            this.setPreferredSize(new Dimension(295, 25));

            updateDate();

            yearLeft.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    yearLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearLeft.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    yearLeft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearLeft.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.YEAR, -1);
                    yearLeft.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    yearLeft.setForeground(Color.BLACK);
                }
            });
            yearRight.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    yearRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearRight.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    yearRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearRight.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.YEAR, 1);
                    yearRight.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    yearRight.setForeground(Color.BLACK);
                }
            });
            monthLeft.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    monthLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthLeft.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    monthLeft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthLeft.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.MONTH, -1);
                    monthLeft.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    monthLeft.setForeground(Color.BLACK);
                }
            });
            monthRight.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    monthRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthRight.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    monthRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthRight.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.MONTH, 1);
                    monthRight.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    monthRight.setForeground(Color.BLACK);
                }
            });
        }

        private void updateDate() {
            center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
        }
    }

    private class JP2 extends JPanel {
        private static final long serialVersionUID = -8176264838786175724L;

        public JP2() {
            this.setPreferredSize(new Dimension(295, 20));
        }

        protected void paintComponent(Graphics g) {
            g.setFont(font);
            g.drawString("星期日 星期一 星期二 星期三 星期四 星期五 星期六", 5, 10);
            g.drawLine(0, 15, getWidth(), 15);
        }

        private void updateDate() {

        }
    }

    private class JP3 extends JPanel {
        private static final long serialVersionUID = 43157272447522985L;

        public JP3() {
            super(new GridLayout(6, 7));
            this.setPreferredSize(new Dimension(295, 100));
            initJP3();
        }

        private void initJP3() {
            updateDate();
        }

        public void updateDate() {
            this.removeAll();
            lm.clear();
            Date temp = select.getTime();
            Calendar select = Calendar.getInstance();
            select.setTime(temp);
            select.set(Calendar.DAY_OF_MONTH, 1);
            int index = select.get(Calendar.DAY_OF_WEEK);
            int sum = (index == 1 ? 8 : index);
            select.add(Calendar.DAY_OF_MONTH, 0 - sum);
            for (int i = 0; i < 42; i++) {
                select.add(Calendar.DAY_OF_MONTH, 1);
                lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH), select.get(Calendar.DAY_OF_MONTH)));
            }
            for (MyLabel my : lm.getLabels()) {
                this.add(my);
            }
            select.setTime(temp);
        }
    }

    private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {
        private static final long serialVersionUID = 3668734399227577214L;
        private int year, month, day;
        private boolean isSelected;

        public MyLabel(int year, int month, int day) {
            super("" + day, JLabel.CENTER);
            this.year = year;
            this.day = day;
            this.month = month;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.setFont(font);
            if (month == select.get(Calendar.MONTH)) {
                this.setForeground(Color.BLACK);
            } else {
                this.setForeground(Color.LIGHT_GRAY);
            }
            if (day == select.get(Calendar.DAY_OF_MONTH)) {
                this.setBackground(new Color(160, 185, 215));
            } else {
                this.setBackground(Color.WHITE);
            }
        }

        public boolean getIsSelected() {
            return isSelected;
        }

        public void setSelected(boolean b, boolean isDrag) {
            isSelected = b;
            if (b && !isDrag) {
                int temp = select.get(Calendar.MONTH);
                select.set(year, month, day);
                if (temp == month) {
                    SwingUtilities.updateComponentTreeUI(jp3);
                } else {
                    refresh();
                }
            }
            this.repaint();
        }

        protected void paintComponent(Graphics g) {
            if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
                //如果当前日期是选择日期,则高亮显示
                g.setColor(new Color(160, 185, 215));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH) && day == now.get(Calendar.DAY_OF_MONTH)) {
                //如果日期和当前日期一样,则用红框
                Graphics2D gd = (Graphics2D) g;
                gd.setColor(Color.RED);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            if (isSelected) {//如果被选中了就画出一个虚线框出来
                Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f, new float[]{2.0f, 2.0f}, 1.0f);
                Graphics2D gd = (Graphics2D) g;
                gd.setStroke(s);
                gd.setColor(Color.BLACK);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            super.paintComponent(g);
        }

        public boolean contains(Point p) {
            return this.getBounds().contains(p);
        }

        private void update() {
            repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            isSelected = true;
            update();
        }

        public void mouseReleased(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
            lm.setSelect(p, false);
            commit();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
            lm.setSelect(p, true);
        }

        public void mouseMoved(MouseEvent e) {
        }

        public int compare(MyLabel o1, MyLabel o2) {
            Calendar c1 = Calendar.getInstance();
            c1.set(o1.year, o2.month, o1.day);
            Calendar c2 = Calendar.getInstance();
            c2.set(o2.year, o2.month, o2.day);
            return c1.compareTo(c2);
        }
    }

    private class LabelManager {
        private List<MyLabel> list;

        private LabelManager() {
            list = new ArrayList<>();
        }

        private List<MyLabel> getLabels() {
            return list;
        }

        private void addLabel(MyLabel my) {
            list.add(my);
        }

        private void clear() {
            list.clear();
        }

        @SuppressWarnings("unused")
        public void setSelect(MyLabel my, boolean b) {
            for (MyLabel m : list) {
                if (m.equals(my)) {
                    m.setSelected(true, b);
                } else {
                    m.setSelected(false, b);
                }
            }
        }

        public void setSelect(Point p, boolean b) {
            //如果是拖动,则要优化一下,以提高效率
            if (b) {
                //表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和
                //将要显示的标签找到了就可以了
                boolean findPrevious = false, findNext = false;
                for (MyLabel m : list) {
                    if (m.contains(p)) {
                        findNext = true;
                        if (m.getIsSelected()) {
                            findPrevious = true;
                        } else {
                            m.setSelected(true, b);
                        }
                    } else if (m.getIsSelected()) {
                        findPrevious = true;
                        m.setSelected(false, b);
                    }
                    if (findPrevious && findNext) {
                        return;
                    }
                }
            } else {
                MyLabel temp = null;
                for (MyLabel m : list) {
                    if (m.contains(p)) {
                        temp = m;
                    } else if (m.getIsSelected()) {
                        m.setSelected(false, b);
                    }
                }
                if (temp != null) {
                    temp.setSelected(true, b);
                }
            }
        }

    }

    private class JP4 extends JPanel {
        private static final long serialVersionUID = -6391305687575714469L;

        public JP4() {
            super(new BorderLayout());
            this.setPreferredSize(new Dimension(295, 20));
            this.setBackground(new Color(160, 185, 215));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            final JLabel jl = new JLabel("今天: " + sdf.format(new Date()));
            jl.setToolTipText("点击选择今天日期");
            this.add(jl, BorderLayout.CENTER);
            jl.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    jl.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    jl.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    jl.setForeground(Color.WHITE);
                    select.setTime(new Date());
                    refresh();
                    commit();
                }

                public void mouseReleased(MouseEvent me) {
                    jl.setForeground(Color.BLACK);
                }
            });
        }

        private void updateDate() {

        }
    }
} 