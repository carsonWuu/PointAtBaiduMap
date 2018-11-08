### 将数据的点在百度地图上标注
#### 一、项目目标
在百度地图上实时展示车辆信息。地图使用百度地图。基本技术：jsp，servlet,mysql等

#### 二、项目需求：
登录界面（不需要注册界面）
自适应展示地图（百度地图，主要是中国）
具有缩放19层效果（1~19）
根据缩放的效果，动态展示数据。数据量可能在几十万个。需要对数据的展示	进行筛选，可能因为数据比较多，不能进行完全展示；也可能是部分省份、城	市数据差异较大，需要对其进行动态筛选。
实时展示：时间间隔为5s/10s，用户可以进行选择。
#### 三、项目思路：
登录界面
验证登录信息，用户名及密码。错误则保留用户名、密码置空；成功则进行跳转，	到地图展示页面。
地图展示：
页面的展示
页面的动态生成
页面的定时刷新
与用户的交互：
缩放
定时刷新间隔设置。
后台处理：
登录前：处理用户的登录信息（/user/userlogin）
登陆后：处理用户的数据展示请求
#### 四、项目计划安排：
1.登录页面		
2.空数据地图		
3.登录页面+与服务器、数据库交互进行验证信息。		
4.地图页面+服务器从数据库得到的数据返回到前端。		
