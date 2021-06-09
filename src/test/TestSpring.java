import com.share.charge.mybatis.generator.mapper.GuestMapper;
import com.share.charge.test.HelloWorld;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
    @Autowired
    private HelloWorld helloWorld;

//    @Autowired
//    private GuestMapper guestMapper;

    @Test
    public void testBean() {
        System.out.println(helloWorld);
    }

    /**
     * test mybatis
     * @throws IOException
     */
    @Test
    public void testMapperM() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //1.获取到sqlsession 不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        GuestMapper mapper = openSession.getMapper(GuestMapper.class);

        System.out.println(mapper.selectByPrimaryKey(0));

        //手动提交数据
        openSession.commit();
        openSession.close();


    }



    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    ApplicationContext applicationContext;
    @Test
    public void testSpringMybatis(){
        guestMapper = applicationContext.getBean(GuestMapper.class);
        System.out.println(guestMapper.selectByPrimaryKey(0));
    }
}
