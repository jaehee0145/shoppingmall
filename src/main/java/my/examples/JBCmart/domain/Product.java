package my.examples.JBCmart.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "product_id")
    private Long productId;

    @Column(name= "product_name")
    private String productName;

    private long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
   private User user;

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ImageFile> imageFiles;

    public Product(){
        imageFiles = new ArrayList<>();
    }
    public void addImageFile(ImageFile imageFile) {
        if(imageFiles == null)
            imageFiles = new ArrayList<>();
        imageFile.setProduct(this); // 쌍방향이기 때문에 this를 참조하도록 한다.
        imageFiles.add(imageFile);
    }


}
