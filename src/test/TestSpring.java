
import com.share.charge.mybatis.generator.mapper.UmsGuestMapper;
import com.share.charge.mybatis.generator.model.UmsGuest;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
    @Autowired
    private HelloWorld helloWorld;

//    @Autowired
//    private GuestMapper umsGuestMapper;

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
        UmsGuestMapper mapper = openSession.getMapper(UmsGuestMapper.class);
        UmsGuest umsGuest = mapper.selectByPrimaryKey(1,"1");

        if(umsGuest !=null ){
            System.out.println(umsGuest.toString());
        }

        //手动提交数据
        openSession.commit();
        openSession.close();


    }

    @Resource
    private UmsGuestMapper umsGuestMapper;
    @Test
    public void testAutoMapper()throws IOException{
        UmsGuest umsGuest = umsGuestMapper.selectByPrimaryKey(1,"1");

        if(umsGuest !=null ){
            System.out.println(umsGuest.toString());
        }


    }


//    @Autowired
//    private UmsGuestMapper umsGuestMapper;
//    @Autowired
//    ApplicationContext applicationContext;
//    @Test
//    public void testSpringMybatis(){
//        umsGuestMapper = applicationContext.getBean(UmsGuestMapper.class);
//        System.out.println(umsGuestMapper.selectByPrimaryKey(0));
//        Guest guest = new Guest();
//        guest.toString();
//    }
}
