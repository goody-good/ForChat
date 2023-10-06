<h3 align="center">移动组实习日记</h3>

#### 2023年9月29日

- 完成登录界面页面布局activity_login.xml

  - 使用约束布局ConstraintLayout
  - 一个用于定义形状和渐变的background_for_sign_in_screen.xml文件，从上到下垂直渐变，起始颜色为半透明红色，中间颜色为半透明黑色，结束颜色为透明色
  - 用TextView做的按钮“注册”、“忘记密码”和“Login”
  - “Login”指定了左右两侧的外边距，引用了background_for_sign_in_button.xml作为背景，其中定义了一个矩形，有从左到右的渐变效果，由黄色渐变为橙色，圆角半径50dp
- 完成注册界面页面布局activity_register.xml，与登录界面大致相同
- LoginActivity，查询输入的数据和数据库内的是否对应，成功则跳转MainActivity，但在SQLite数据库上出了一些问题，点注册则跳转RegisterActivity
- RegisterActivity，用来注册，但也在数据库上面出现一些问题
- 定义了User类，内含id,Mobile,password

#### 2023年9月30日

- 进行反思，我在没有想清楚想做什么的情况下就急于动手，这导致我在写代码的时候思维混乱，隔了一天有点茫然，我不得不重新开始梳理。

- 解决了9.29日数据库方面的问题，登录和注册都可以实现了

- 做了简单的聊天气泡，是三个圆角一个方角的矩形，区分接收和发送

- 写了好友界面，但是xml出现 Rendering Problems和Resource errors，在查怎么处理，以前没遇到过

- 尝试新学CircleImageView，当头像

#### 2023年10月1日

- 完成了好友分组，有group_item.xml，child_item.xml，用到了二维数组存储好友的信息，虽然丑，但决定先把功能实现了再考虑美观的问题
- FriendAdapter有报错，且报错会有奇奇怪怪的乱码，纠结半天后发现少了一个import
- 好友直接写死了，没有用数据库
- 排了很久的报错，终于能下载在手机上，然而试试发现无法从好友分组跳转到聊天，还不知道是哪里出了问题
- 做了ChatActivity，好让每个好友的聊天继承
- activity_chat作为基本的聊天布局，以备继承

#### 2023年10月2日

- 发现自己昨天为什么不能跳转了，设好了Intent之后没有写启动活动的语句，很低级的错误
- 感觉对于一个新手，还是先特殊再一般比较容易一些，于是考虑先完成一个好友的聊天功能
- 设置好startActivity后，发现可以跳转但是会闪退，然后就是努力地找哪里出错了
  - 发现自己有些应该用成员变量的地方用了局部变量
  - 查看Logcat发现了一些拼写错误，比如建立数据表的时候integer写成了interger
  - 发现聊天功能的适配器继承了BaseAdapter而不是RecyclerView.Adapter，然后重写的也是适用于ListView的方法，改过来就对了
- 成功从好友分组跳转到和其中一个好友聊天，并且能够发出去消息

#### 2023年10月3日

cursor没关
