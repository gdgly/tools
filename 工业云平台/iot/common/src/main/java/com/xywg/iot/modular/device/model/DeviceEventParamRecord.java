package com.xywg.iot.modular.device.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hy
 * @since 2018-12-11
 */
@TableName("t_device_event_param_record")
@Data
public class DeviceEventParamRecord extends Model<DeviceEventParamRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 属性id
     */
	@TableField("param_id")
	private Integer paramId;
    /**
     * 明细id
     */
	@TableField("record_id")
	private Integer recordId;
    /**
     * 数据
     */
	@TableField("data_value")
	private String dataValue;

	@TableField("is_del")
	@TableLogic
	private int isDel;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 创建人
	 */
	@TableField(value = "create_user",fill = FieldFill.INSERT)
	private String createUser;
	/**
	 * 创建人姓名
	 */
	@TableField(value = "create_user_name",fill = FieldFill.INSERT)
	private String createUserName;
	/**
	 * 更新时间
	 */
	@TableField(value = "modify_time",fill = FieldFill.UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifyTime;
	/**
	 * 更新人
	 */
	@TableField(value = "modify_user",fill = FieldFill.UPDATE)
	private String modifyUser;
	/**
	 * 更新人姓名
	 */
	@TableField(value = "modify_user_name",fill = FieldFill.UPDATE)
	private String modifyUserName;
	public DeviceEventParamRecord() {
	}

	public DeviceEventParamRecord(Integer paramId, Integer recordId, String dataValue) {
		this.paramId = paramId;
		this.recordId = recordId;
		this.dataValue = dataValue;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}
