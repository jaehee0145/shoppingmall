package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Category;
import my.examples.JBCmart.domain.ImageFile;
import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.security.BlogSecurityUser;
import my.examples.JBCmart.service.CategoryService;
import my.examples.JBCmart.service.ImageFileService;
import my.examples.JBCmart.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageFileService imageFileService;

    @GetMapping("/{productId}")
    public String view(@PathVariable(name = "productId") Long productId, Model model ){
        Product product = productService.getProducts(productId);
        model.addAttribute("product", product);
        return "products/view";

    }

    @GetMapping("/images/{id}")
    @ResponseBody // 컨트롤러안에서 직접 response를 이용하여 결과를 출력할 때 사용
    public void downloadImage(
            @PathVariable(name = "id") Long id,
            HttpServletResponse response
    ) {
        ImageFile imageFile = imageFileService.getImageFile(id);
        response.setContentType(imageFile.getMimeType());

        try(FileInputStream fis = new FileInputStream(imageFile.getSaveFileName());
            OutputStream out = response.getOutputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;

            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @GetMapping("/add")
    public String addform(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "products/addform";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") long price,
            @RequestParam(name = "categoryId") Long categoryId,
            @RequestParam(name = "image") MultipartFile[] images) {

        Assert.hasText(name, "no name");

        BlogSecurityUser securityUser =
                (BlogSecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(securityUser.getName());
        Product product = new Product();
        product.setProductName(name);
        product.setPrice(price);

        if(images != null && images.length > 0) {
            for (MultipartFile image : images) {
                ImageFile imageFile = new ImageFile();
                imageFile.setLength(image.getSize());
                imageFile.setMimeType(image.getContentType());
                imageFile.setName(image.getOriginalFilename());
                // 파일 저장
                // /tmp/2019/2/12/123421-12341234-12341234-123423142
                String saveFileName = saveFile(image);

                imageFile.setSaveFileName(saveFileName); // save되는 파일명
                product.addImageFile(imageFile);
            }
        }

        productService.addProduct(product, categoryId, securityUser.getId());
        productService.addProduct(product, categoryId);

        return "redirect:/main";
    }

    private String saveFile(MultipartFile image) {
        String dir = "/tmp/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";
        File dirFile = new File(dir);
        dirFile.mkdirs(); // 디렉토리가 없을 경우 만든다. 퍼미션이 없으면 생성안될 수 있다.
        dir = dir + UUID.randomUUID().toString();

        try(FileOutputStream fos = new FileOutputStream(dir);
            InputStream in = image.getInputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;
            while((readCount = in.read(buffer)) != -1){
                fos.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return dir;
    }
}
