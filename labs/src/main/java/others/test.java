package others;

public class test {
    public static void main(String[] args) {
        boolean flag = false;
        String name = "position:0,0by client6203" + (flag? 1 : 0);
        System.out.println(name.substring(15));
        System.out.println(name.substring(9,12));
        System.out.println(Integer.parseInt(name.substring(9,10)));
        System.out.println(Integer.parseInt(name.substring(11,12)));
        System.out.println(name.substring(name.length()-1));

        String msg = "last:" + 1 + "," + 2;
        System.out.println(msg);
        System.out.println(Integer.parseInt(msg.substring(5,6)));
        System.out.println(Integer.parseInt(msg.substring(7,8)));

        name = "hello";
        String op_name = "test";
        int my_turn = 1;
        String board = "0,0,0,0,1,0,2,0";
        String password = "123456";
        String sql = "insert into users value ('" + name + "', '" + password + "');";
        System.out.println(sql);
        sql = "insert into record value ('" + name +  "', 0, 0, 0, 0);";
        System.out.println(sql);
        System.out.println(sql.substring(0,sql.length()-1));
        sql = "insert into game_on value ('" + name +"', '" + op_name + "', " + my_turn + ", '" + board + "');";
        System.out.println(sql);
        System.out.println(sql.substring(sql.length()-1));
    }
}
