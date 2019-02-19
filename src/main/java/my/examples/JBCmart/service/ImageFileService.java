package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.ImageFile;
import my.examples.JBCmart.repository.ImageFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageFileService {
    private final ImageFileRepository imageFileRepository;

    @Transactional(readOnly = true)
    public ImageFile getImageFile(Long id){
        return imageFileRepository.findById(id).get();
    }
}
