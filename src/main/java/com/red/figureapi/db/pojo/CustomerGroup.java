package com.red.figureapi.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * customer_group
 * @author 
 */
@Data
public class CustomerGroup implements Serializable {
    private Integer memberId;

    private Integer id;

    private Integer classify;

    private static final long serialVersionUID = 1L;
}