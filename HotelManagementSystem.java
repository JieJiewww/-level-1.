package jiudian;
import java.util.*;
public class HotelManagementSystem {
    //订房
    private static void bookRoom(List<Room> rooms, Scanner scanner) {
        System.out.print("请输入您要预订的房间号:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // 消耗nextInt()后遗留的换行符

        Room roomToBook = null;
        for (Room room : rooms) {  //运用for-each循环 遍历rooms 列表
            if (room.getRoomNumber() == roomNumber && room.getStatus() == Room.RoomStatus.AVAILABLE) {
                roomToBook = room;
                break; // 找到后立即退出循环
            }
        }

        if (roomToBook != null) {
            System.out.print("请输入您的姓名:");
            String name = scanner.nextLine();
            roomToBook.setName(name);
            System.out.print("请输入你的身份证号码:");
            String id = scanner.nextLine();
            roomToBook.setId(id);

            //进行余额计算

            System.out.print("请输入您要预订几晚:");
            int night = scanner.nextInt();
            int sum = night*roomToBook.getRoomprice();
            System.out.println("请使用现金支付"+night+"晚"+"一共"+sum+"的费用");
            System.out.print("请输入您的票面:");
            int pay = scanner.nextInt();
            if(pay>=sum){
                roomToBook.setStatus(Room.RoomStatus.BOOKED);
                int total = pay - sum;
                System.out.println("房间预订成功！"+"找您"+total+"元");
            }else {
                System.out.println("支付失败，请重新进行预订");
            }
        } else {
            System.out.println("房间不存在或已被预订。");
        }
        System.out.println("--------------------------------------------");
    }
    //查房
    private static void checkRoom(List<Room>rooms){
        int s=0;
        for(Room room:rooms){
            if(room.getStatus()==Room.RoomStatus.AVAILABLE){
                s++;
                System.out.println("房间号："+room.getRoomNumber()+" 房间类型："+room.getRoomType()+" 时价："+room.getRoomprice());
            }
        }
        System.out.println("当前剩余"+s+"间");
        System.out.println("--------------------------------------------");
    }
    //退房
    private static void Check_Out(Scanner scanner,List<Room>rooms){ //房间号
        System.out.println("请输入房间号:");
        int roomNumber = scanner.nextInt();
        Room youroom=null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getStatus() == Room.RoomStatus.BOOKED) {
                youroom = room;
                break; // 找到后立即退出循环
            }
        }
        if(youroom!=null){
            youroom.setStatus(Room.RoomStatus.AVAILABLE);
            youroom.setId(null);
            youroom.setName(null);
            System.out.println("---退房成功---");
        }
        else{
            System.out.println("房间不存在或已退房。");
        }
    }
    //管理
    private static void Management(Scanner scanner,List<Room>rooms){
        System.out.println("请输入操作数（1.添加房间 2.删除房间）:");
        int choice = scanner.nextInt();
        int roomnum=0;
        String roomtype=null;
        Room op=null;
        switch (choice){
            case 1:
                System.out.println("请输入该房间号码");
                roomnum = scanner.nextInt();
                scanner.nextLine();

                System.out.println("请输入该房间类型:");
                roomtype = scanner.nextLine();


                System.out.println("请输入该房间时价");
                int roomprice = scanner.nextInt();

                rooms.add(new Room(roomnum,roomtype,roomprice,Room.RoomStatus.AVAILABLE));
                System.out.println("添加房间成功");
                break;
            case 2:
                System.out.println("请输入该房间号码");
                roomnum = scanner.nextInt();
                scanner.nextLine();
                //建迭代器遍历rooms列表
                Iterator<Room> it = rooms.iterator();
                while(it.hasNext()){
                    Room currentRoom = it.next();
                    if(currentRoom.getRoomNumber()==roomnum){
                        it.remove();
                        System.out.println("房间删除成功");
                        break;
                    }
                }
                if (!it.hasNext()) {
                    System.out.println("未找到指定房间号码的房间");
                }
                break;
        }
    }
    //检查管理员账号密码
    private static boolean Check(Scanner scanner,administer admin){
        System.out.println("请输入账号:");
        int id = scanner.nextInt();
        System.out.println("请输入密码:");
        int password = scanner.nextInt();
        if(admin.getId() == id &&admin.getPassword() == password )return true;
        System.out.println("您输入的账号或密码有误");
        return false;
    }


    public static void main(String[] args) {
        // 创建一个房间列表
        List<Room> rooms = new ArrayList<>();
        {
            rooms.add(new Room(8001, "大床房",331, Room.RoomStatus.AVAILABLE));
            rooms.add(new Room(8002, "双床房",400, Room.RoomStatus.AVAILABLE));
            rooms.add(new Room(8003, "高级大床房",366, Room.RoomStatus.AVAILABLE));
            rooms.add(new Room(8004, "高级大床房",366, Room.RoomStatus.AVAILABLE));
            rooms.add(new Room(8005, "高级双床房",450, Room.RoomStatus.AVAILABLE));
            rooms.add(new Room(8006, "高级双床房",450, Room.RoomStatus.AVAILABLE));
        }  //初始化房子

        administer admin = new administer();  //建 管理员对象

        System.out.println("-----欢迎光临亚朵酒店-----");

        Scanner scanner = new Scanner(System.in);

        int choice;  //根据choice的值进行不同功能的跳转
        do {
            System.out.println("请选择操作：");
            System.out.println("1. 查看可以预订的房间");
            System.out.println("2. 预订房间");
            System.out.println("3. 退房操作");
            System.out.println("4. 管理");
            System.out.println("5. 退出");
            choice = scanner.nextInt();
            scanner.nextLine(); // 消耗nextInt()后遗留的换行符
            switch (choice) {
                case 1:
                    checkRoom(rooms); //调用查看房间的函数
                    break;
                case 2:
                    bookRoom(rooms, scanner); // 调用预订房间的函数
                    break;
                case 3:
                    Check_Out(scanner,rooms);
                    break;
                case 4:
                    if(Check(scanner,admin)) {
                        Management(scanner, rooms);
                    }
                    break;
                case 5:
                    System.out.println("退出系统");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        } while (choice != 5);
        scanner.close();
        System.out.println("欢迎下次光临亚朵酒店");
    }
}