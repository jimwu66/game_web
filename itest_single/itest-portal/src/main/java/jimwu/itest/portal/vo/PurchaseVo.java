package jimwu.itest.portal.vo;

import jimwu.itest.portal.model.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class PurchaseVo {

    private Integer coin;
    private List<ProductVo> products;
}
