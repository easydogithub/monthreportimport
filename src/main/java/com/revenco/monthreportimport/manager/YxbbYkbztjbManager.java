package com.revenco.monthreportimport.manager;

import java.util.List;

import org.hibernate.Session;

import com.revenco.monthreportimport.domain.YxbbYkbztjb;
import com.revenco.monthreportimport.util.HibernateUtil;

public class YxbbYkbztjbManager {

	public void storeYxbbYkbztjb(YxbbYkbztjb yxbbYkbztjb) {
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		session.beginTransaction();
		session.save(yxbbYkbztjb);
		session.getTransaction().commit();
	}

	public void storeYxbbYkbztjbs(List<YxbbYkbztjb> yxbbYkbztjbs) {
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		session.beginTransaction();
		int count = 0;
		for (YxbbYkbztjb yxbbYkbztjb : yxbbYkbztjbs) {
			session.save(yxbbYkbztjb);
			if (count++ % 40 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
	}
	
	public String getYxbbYkbztjbAsSql(YxbbYkbztjb yxbbYkbztjb){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into TGTYXASZG.YXBB_YKBZTJB(")
		.append("GSDM")
		.append(",NY")
		.append(",BBXH")
		.append(",XHMC")
		.append(",YDLB_ID")
		.append(",ZZJRL")
		.append(",ZZJHS")
		.append(",YJXZRL")
		.append(",YJXZHS")
		.append(",YJXZJDRL")
		.append(",YJXZJDHS")
		.append(",YJZRRL")
		.append(",YJZRHS")
		.append(",YJZRJDRL")
		.append(",YJZRJDHS")
		.append(",LSJDRL")
		.append(",LSJDHS")
		.append(",LSJDTCRL")
		.append(",LSJDTCHS")
		.append(",XHRL")
		.append(",XHHS")
		.append(",JRRL")
		.append(",JRHS")
		.append(",JRFRRL")
		.append(",JRFRHS")
		.append(",ZTTRL")
		.append(",ZTTHS")
		.append(",ZTFRL")
		.append(",ZTFHS")
		.append(",FBQRL")
		.append(",FBQHS")
		.append(",FBHRL")
		.append(",FBHHS")
		.append(",GYRL")
		.append(",GYHS")
		.append(",BYZZRL")
		.append(",BYZZHS")
		.append(",NLJZZRL")
		.append(",NLJZZHS")
		.append(",NLJZZRL_TQ")
		.append(",NLJZZHS_TQ")
		.append(",DYDJ")
		.append(",JLZT_ID")
		.append(")")
		.append(" values")
		.append("(")
		.append(yxbbYkbztjb.toString())
		.append(");");
		
		return sb + "";
	}
	
	public String getYxbbYkbztjbAsSqls(List<YxbbYkbztjb> yxbbYkbztjbs){
		StringBuffer sb = new StringBuffer();
		for(YxbbYkbztjb yxbbYkbztjb:yxbbYkbztjbs){
			sb.append(getYxbbYkbztjbAsSql(yxbbYkbztjb)).append("\n");
		}
		return sb + "";
		
	}

}
