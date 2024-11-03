package com.ubom;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nozomi.UsedBookOnlineMarketplaceApplication;
import com.nozomi.mapper.BookMapper;
import com.nozomi.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UsedBookOnlineMarketplaceApplication .class)
class UsedBookOnlineMarketplaceApplicationTests {

    @Autowired
    private BookMapper bookMapper;
//    @Override
//    public List<Book> List() {
//        return bookMapper.List();
//    }

    @Test
    public void test() {
//        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
////修改书名里有a且价格大于30，或图片为null的书
//        queryWrapper.gt("price",30)
//                .like("bookname","a")
//                .or()
//                .isNull("image");
////or()之后的条件与之前的条件是或的关系
//        Book book = new Book();
//        book.setBookname("b");
//        book.setPrice(40F);
//        book.setImage(null);
////修改满足条件的数据
//        int result =bookMapper.update(book,queryWrapper);
//        System.out.println(result);

        Page<Book> page = new Page<>(1, 5);
        bookMapper.selectPage(page, null);
        System.out.println(page.getRecords());


    }

}
