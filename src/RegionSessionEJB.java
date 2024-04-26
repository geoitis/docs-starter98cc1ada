/**
 * RegionSessionEJB
 *
 * CONFIDENTIAL AND PROPRIETARY SOURCE CODE OF ITIS Ltd
 *
 * Copyright (C) 2001-2002 ITIS Ltd. All Rights Reserved.
 * Use of this source code is subject to the terms of the applicable license agreement.
 *
 * The copyright notice(s) in this source code does not indicate actual or intended
 * publication of this source code.
 *
 * Created on : 09-12-2002
 * Target OS  : Windows 984.10 - 1.4.0_01
 *
 * ------------------------------
 * CHANGE REVISION
 * ------------------------------
 * DATE					AUTHOR				REVISION					REMARKS
 * 09-12-2002		bill					1.0							First release.
 *
 * -------------------------------
 * CLASS DESCRIPTION
 * -------------------------------
 * This class is the implementation for the
 *
 */
package com.itis.comm.ejb.basic.region; 

import javax.naming.NamingException;

import com.itis.comm.ejb.basic.MultiSiteSyncHandler;
import com.itis.comm.system.util.PropertyConstants;
import com.itis.comm.system.vo.region.RegionConstants;
import com.itis.comm.system.vo.region.RegionValueObject;
import com.itis.framework2.AbstractValueObject;
import com.itis.framework2.ejb.SessionBase;
import com.itis.framework2.ejb.SessionHome;
import com.itis.framework2.pool.RemoteRefPool;
import com.itis.teg.comm.ejb.addon.tegdataexport.TegDataExportSessionHandler;
import com.itis.teg.comm.system.vo.tegdataexport.TegDataExportConstants;

/**
 * Title: Description: Generated by ITIS EJBGen Copyright: Copyright (c) 2002
 * Company: ITIS Technologies
 *
 * @author
 * @version 1.0
 */

public class RegionSessionEJB extends SessionBase implements RegionConstants {
	public static String TABLE = "region";
	public static String ORDER_BY = null; // put the order by field here

	public RegionSessionEJB() {
	}

	@Override
	/**
	 * Retrieves the name of the table.
	 *
	 * @return The name of the table as a String.
 	*/
	public String getTableName() {
		return TABLE;
	}

	@Override
	/**
	 * Returns the ORDER_BY value.
	 *
	 * @return the ORDER_BY value
 	*/
	public String getOrderBy() {
		return ORDER_BY;
	}

	@Override
	/**
	 * Creates a new instance of AbstractValueObject by instantiating RegionValueObject.
	 *
	 * @return a new instance of AbstractValueObject
	 * @throws SomeException if there is an issue with creating the value object
 	*/
	public AbstractValueObject createValueObject() {
		return new RegionValueObject();
	}

	@Override
	/**
	 * Process the child with the given name and reindex it.
	 *
	 * @param childName The name of the child to be processed.
	 * @param parent The parent object of type AbstractValueObject.
	 * @param child The child object of type AbstractValueObject.
	 * @param index The index of the child.
	 * @throws SomeException If an error occurs during processing.
 	*/
	protected void processChild(String childName, AbstractValueObject parent, AbstractValueObject child, int index) {
		// put reindexing of child here
	}

	@Override
	/**
	 * Checks if the specified child entity supports cascade delete.
	 *
	 * @param name the name of the child entity
	 * @return true if the child entity supports cascade delete, false otherwise
	 * @throws NullPointerException if the specified name is null
 	*/
	protected boolean isCascadeDeleteChild(String name) {
		return true;
	}

	@Override
	/**
	 * Initializes the session with necessary listeners and handlers.
	 *
	 * @throws NamingException if a naming exception occurs during initialization
 	*/
	protected void init() throws NamingException {
		super.init();

		addSessionBaseListener(
				new MultiSiteSyncHandler(SYNC_INDICATOR, RemoteRefPool.getHome("comm/RegionSession", SessionHome.class),
						PropertyConstants.REGION_AUTO_SYNC_ENABLED));
		addSessionBaseListener(new TegDataExportSessionHandler(TegDataExportConstants.REGION,
				(val -> new String[] { val.getString(REGION_CODE) }))
						.setkey1Mapper((val -> val.getString(APPLICATION_ID)))
						.setkey2Mapper((val -> val.getString(COMPY_ID)))
						.setkey3Mapper((val -> val.getString(SOURCE_ID)))
						.setkey4Mapper((val -> val.getString(SITE_ID))));

	}

}
