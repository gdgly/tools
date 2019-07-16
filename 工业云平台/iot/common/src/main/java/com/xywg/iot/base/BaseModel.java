package com.xywg.iot.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : wangyifei
 * Description
 * Date: Created in 15:37 2018/12/27
 * Modified By : wangyifei
 */
@Getter
@Setter
public abstract class BaseModel<T> extends Model<BaseModel<T>>  {
    @TableLogic
    @TableField(value = "is_del",fill = FieldFill.INSERT)
    private Integer isDel;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;
    /**
     * 创建人姓名
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;
    /**
     * 更新人
     */
    @TableField(value = "modify_user", fill = FieldFill.UPDATE)
    private String modifyUser;
    /**
     * 更新人姓名
     */
    @TableField(value = "modify_user_name", fill = FieldFill.UPDATE)
    private String modifyUserName;

}
