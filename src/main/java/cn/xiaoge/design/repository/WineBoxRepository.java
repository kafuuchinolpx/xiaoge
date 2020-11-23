package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.WineBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WineBoxRepository extends JpaRepository<WineBox, Integer> {

    List<WineBox> findAllByGroupId(int i);

    List<WineBox> findByGroupId(int i);

    List<WineBox> findByGroupIdAndLength(Integer id, int length);

    List<WineBox> findAllByDeleteState(int i);

    Page<WineBox> findAllByDeleteState(Pageable var1, int i);

    List<WineBox> findAllByBoxTypeIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, int i, int length, int i1);

    List<WineBox> findAllByBoxTypeIdAndMaterialIdAndStyleIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer materialId, Integer styleId, Integer purposeId, int i, int length, int i1);

    List<WineBox> findAllByDeleteStateAndMaterialId(int i, Integer materialId);

    WineBox findAllById(Integer id);

    List<WineBox> getAllByRecommend(int i);

    List<WineBox> findAllByBoxTypeIdAndStyleIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer styleId, Integer purposeId, int i, int length, int i1);


    List<WineBox> findAllByBoxTypeIdAndMaterialIdAndStyleIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer materialId, Integer styleId, int i, int length, int i1);


    List<WineBox> findAllByBoxTypeIdAndMaterialIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer materialId, Integer purposeId, int i, int length, int i1);

    List<WineBox> findAllByBoxTypeIdAndStyleIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer styleId, int i, int length, int i1);

    List<WineBox> findAllByBoxTypeIdAndMaterialIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer materialId, int i, int length, int i1);

    List<WineBox> findAllByBoxTypeIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(Integer boxTypeId, Integer purposeId, int i, int length, int i1);

}
