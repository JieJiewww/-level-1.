package jiudian;

public class Room {
    // 定义房间状态的枚举类型
    public enum RoomStatus {  //定义一个枚举 “类型”
        AVAILABLE, BOOKED,
    }

    private int roomNumber;  //房号
    private String roomType;//房型
    private int roomprice;//房价
    private RoomStatus status;  //枚举类型变量 其值为
    private String name;//住户的姓名
    private String id;//住户的身份证

    //以下则是get set方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(int roomprice) {
        this.roomprice = roomprice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    //构造方法
    public Room(int roomNumber, String roomType,int roomprice ,RoomStatus status) {
        this.roomNumber = roomNumber;
        this.roomType=roomType;
        this.status=status;
        this.roomprice=roomprice;
    }
}
