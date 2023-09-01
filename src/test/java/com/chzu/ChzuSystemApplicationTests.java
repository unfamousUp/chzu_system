package com.chzu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chzu.dto.RoleDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.*;
import com.chzu.mapper.*;
import com.chzu.service.EventsService;
import com.chzu.utils.ConvertToUUID;
import com.chzu.utils.JwtUtil;
import com.chzu.utils.R;
import com.chzu.vo.EventsWithOrgVo;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.*;

@SpringBootTest
@Slf4j
class ChzuSystemApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EventsMapper eventsMapper;

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    EventsService eventsService;

    // private Jedis jedis;

    @BeforeEach
    void testSetUpJedis(){
        // 建立连接
        // jedis = JedisConnectionFactory.getJedis();
        // 设置密码
        // jedis.auth("123456");
        // 选择库
        // jedis.select(0);
    }

    @Test
    void testString(){
        // 存入数据
        // String result = jedis.set("name", "虎哥");
        // System.out.println(result);
        // 获取数据
        // String name = jedis.get("name");
        // System.out.println(name);
    }
    
    @Test
    void testHash(){
        // jedis.hset("user:1","name","chenjiujia");
        // jedis.hset("user:1","age","21");
        // Map<String, String> hashMap = jedis.hgetAll("user:1");
        // log.info("{}",hashMap);
    }

    @AfterEach
    void testDown(){
        // if (jedis != null){
        //     jedis.close();
        // }
    }

    @Test
    void Test(){
        List<Events> events = eventsMapper.getToDoEventsByOrgNameForAdminUser("待办", 1, "滁州");
        System.out.println(events);
    }

    @Test
    void contextLoads() {
        R<List<EventsWithOrgVo>> list = eventsService.getWaitToEventsInfo(1);
        // List<Events> list = eventsMapper.getEventsByAssignedUserId(1);
        // userMapper.getUserByOrgId(1002)
        log.info("{}",list);
        // List<Events> list = eventsMapper.getToDoEventsForAdminUser(1, "待办");
        // List<Events> list2 = eventsMapper.getToDoEventsForInstitutionUser(2, "待办");
        // UpdateEventsInfoDTO updateEventsInfoDTO = new UpdateEventsInfoDTO();
        // updateEventsInfoDTO.setEventId(101);
        // updateEventsInfoDTO.setEventsStatus("在办");
        // updateEventsInfoDTO.setEventStatusInstitution("待办");
        // updateEventsInfoDTO.setUserId(2);
        // Integer count = eventsMapper.updateEventsInfoForAdminUser(updateEventsInfoDTO);
        // log.info("{}",count);
        // list.forEach(System.out::println);
        // list2.forEach(System.out::println);
    }

    @Test
    void testGetEventsWithWorkFlow(){
        // List<Events> eventsWithSteps = eventsMapper.getEventsWithSteps(101);
        // System.out.println(eventsWithSteps);

    }

    @Test
    void testGetPemissionByRolename(){
        List<String> roles = roleMapper.getRolenameByUsername("wxb");
        List<String> permissionInfo = userMapper.getUserPermissionInfo(roles);
        permissionInfo.forEach(System.out::println);
    }

    @Test
    void testSelectUserWithRoles(){
        User user1 = userMapper.getUserWithRoles("wxb");
        System.out.println(user1);
    }

    @Test
    void testFindRoleByUsername(){
        List<Role> list = roleMapper.getRoleByUsername("wxb");
        List<String> roleInfo = roleMapper.getRolenameByUsername("wxb");
        list.forEach(System.out::println);
        roleInfo.forEach(System.out::println);
    }

    @Test
        // 插入
    void testInsert() {
        Book book = new Book();
        book.setBookName("刀剑神域3");
        book.setPrice("100");
        // 如果插入部分数据，其它没设置值的数据库字段需要有默认值
        int result = bookMapper.insert(book);
        System.out.println("数据库受影响的行数：" + result);
        // 获取自增长后的id值，自增长后的id会回填到user对象中
        System.out.println("id => " + book.getId());
    }

    @Test
        // 根据id更新数据
    void testUpdateById() {
        Book book = new Book();
        book.setId(441); // 条件：根据id更新
        book.setBookName("未闻花名"); // 更新的字段
        book.setPrice("1000");
        int result = bookMapper.updateById(book);
        System.out.println("result = " + result);
    }

    // 更新数据
    @Test
    void testUpdate() {
        Book book = new Book();
        book.setBookName("未闻花名"); // 更新的字段
        book.setPrice("1001");
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 查询条件：column数据库字段名,val条件的值。相当于where column = #{val}
        wrapper.eq("id", 441);
        int result = bookMapper.update(book, wrapper);
        System.out.println("result = " + result);
    }

    /**
     * 更新数据
     */
    @Test
    void testUpdate2() {
        UpdateWrapper<Book> wrapper = new UpdateWrapper<>();
        wrapper.set("book_name", "咒术回战"). // set:更新的字段
                eq("id", 441); // 更新的条件
        int result = bookMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }

    /**
     * 根据id删除数据
     */
    @Test
    void testDeleteById() {
        int result = bookMapper.deleteById(443);
        System.out.println("result = " + result);
    }

    /**
     * 根据map删除数据，多条件之间是and关系
     */
    @Test
    void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("book_name", "刀剑神域2");
        map.put("price", "100");
        int result = bookMapper.deleteByMap(map);
        System.out.println("result = " + result);
    }

    /**
     * 根据包装条件查询然后删除
     */
    @Test
    void testDelete() {
        // QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // wrapper.eq("id",444);

        Book book = new Book();
        book.setId(441);
        QueryWrapper<Book> wrapper = new QueryWrapper<>(book);
        int result = bookMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    /**
     * 批量删除
     */
    @Test
    void testDeleteBatchIds() {
        // 相当于 WHERE id IN ( ? , ? )
        int result = bookMapper.deleteBatchIds(Arrays.asList(440, 441));
        System.out.println("result = " + result);
    }

    /**
     * 根据id查询
     */
    @Test
    void testSelectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    /**
     * 根据id批量查询
     */
    @Test
    void testSelectBatchIds() {
        // 相当于WHERE user_id IN ( ? , ? )
        List<User> user = userMapper.selectBatchIds(Arrays.asList(1, 2));
        for (User myUser :
                user) {
            System.out.println(myUser);
        }
    }

    /**
     * 根据QueryWrapper条件查询一条数据
     */
    @Test
    void testSelectOne() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 查询条件
        wrapper.eq("id", 439);
        Book book = bookMapper.selectOne(wrapper);
        System.out.println(book);
    }

    /**
     * 查询符合条件的记录条数
     */
    @Test
    void testSelectCount() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 查询条件：grant than
        wrapper.gt("id", 300);
        Integer count = bookMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    /**
     * 查询为list集合
     */
    @Test
    void testSelectList() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 模糊查询: WHERE (book_name LIKE %Spring%)
        wrapper.like("book_name", "Spring");
        List<Book> bookList = bookMapper.selectList(wrapper);
        bookList.forEach(System.out::println);
    }

    /**
     * 测试分页查询
     */
    @Test
    void testSelectPage() {
        /**
         * current:当前页码
         * size:设置每页数据个数
         */
        Page<Book> page = new Page<>(2, 5);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lt("id", 200);
        Page<Book> iPage = bookMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("数据总页数：" + iPage.getPages());
        System.out.println("当前页数：" + iPage.getCurrent());
        // 获取分页数据
        List<Book> records = iPage.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * allEq
     */
    @Test
    void testAllEq() {
        Map<String, Object> map = new HashMap<>();
        map.put("writer", "某某");
        map.put("price", "19.9");
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // WHERE (price = ? AND writer = ?)
        wrapper.allEq(map);
        List<Book> list = bookMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    void resetPassword() {
        String password = "Czhou@2023";
        Md5Hash md5Hash = new Md5Hash(password,"salt",1024);
        log.info("{}",md5Hash);
        Integer count = userMapper.selectCount(null);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("password", md5Hash.toHex());
        userMapper.update(null, wrapper);
    }

    @Test
    void testSensitiveWordMapper() {
        /**
         * 查询所有数据
         */
        List<SensitiveWord> list = sensitiveWordMapper.selectList(null);
        for (SensitiveWord sensitiveWord :
                list) {
            System.out.println(sensitiveWord);
        }
        // System.out.println(list);
    }

    @Test
    void jwtTest() {
        // String token = JwtUtil.getJwtToken("1", "wxb");
        // log.info("token:{}", token);
        // Claims claims = JwtUtil.parseJwt(token);
        // log.info("claims:{}", claims);
        // log.info("--------------------------------");
        // log.info("userId:{}", claims.get("userId"));
        // log.info("username:{}", claims.get("username"));
        // log.info("id:{}", claims.getId());
        // log.info("subject:{}", claims.getSubject());
        // log.info("id:{}", claims.getExpiration()); // 有效期截止时间
    }

    @Test
    void testShiro() {
        // 1.初始化并获取认证管理器SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 3.创建token对象，传递用户名密码
        AuthenticationToken token = new UsernamePasswordToken("wxb", "Czhou@2023");
        // 4.完成登录
        subject.login(token);
        log.info("登录成功");
        // 5.角色判断
        boolean hasRole = subject.hasRole("role1");
        System.out.println("是否拥有此角色 = " + hasRole);
        // 6.权限判断
        boolean permitted = subject.isPermitted("user:insert");
        System.out.println("是否拥有此权限 = " + permitted);

    }

    /**
     * shiro加密
     */
    @Test
    void shiroMD5() {
        // 密码明文
        String password = "Czhou@2023";
        // 使用MD5加密
        Md5Hash md5Hash = new Md5Hash(password);
        log.info("md5加密：{}", md5Hash);
        // 使用带盐的MD5加密，盐就是在密码明文后拼接新字符串，然后再进行加密
        Md5Hash md5HashBySalt = new Md5Hash(password, "salt");
        log.info("md5带盐加密：{}", md5HashBySalt);
        // 为了保证安全避免被破解，可以多次迭代加密，保证数据安全
        Md5Hash md5HashBySaltIteration = new Md5Hash(password, "salt", 3);
        log.info("md5带盐迭代加密：{}", md5HashBySaltIteration);
        log.info("md5带盐迭代加密：{}", md5HashBySaltIteration.toHex());
        // 使用父类进行加密，可以选择加密算法
        SimpleHash simpleHash = new SimpleHash("MD5", password, "salt", 3);
        log.info("simpleHash：{}", simpleHash.toHex());
    }

    @Test
    void jwtUtilTest(){
        // String jwtToken = JwtUtil.getJwtToken("wxb", "admin");
        // log.info("{}",jwtToken);
        // boolean wxb = JwtUtil.verifyToken(jwtToken, "wxb","admin");
        // log.info("{},",wxb);
    }
}
