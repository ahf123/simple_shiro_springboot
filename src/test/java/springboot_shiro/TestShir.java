package springboot_shiro;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahf.shiro.mapper.UserDao;
import com.ahf.shiro.pojo.Users;

public class TestShir {

	@Autowired
	private UserDao userDao;

	@Test
	public void test01() {
		System.out.println(userDao);
		Users user = userDao.findUserByUserName("admin");
		System.out.println(user);
	}

}
