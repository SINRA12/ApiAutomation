package com.test.Util.mysql;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MYSQLManager {
private Map<DBConfig, DBBase> dbMap;

private MYSQLManager() {
this.dbMap = new HashMap();
}

public static MYSQLManager getInstance() {
return MYSQLManager.InstanceHolder.instance_;
}

public DBBase getDatabase(DBConfig conf) throws Exception {
DBBase helper = (DBBase)this.dbMap.get(conf);
if (helper == null) {
synchronized(this.dbMap) {
helper = (DBBase)this.dbMap.get(conf);
if (helper == null) {
helper = new DBBase(conf);
this.dbMap.put(conf, helper);
}
}
}

return helper;
}

public Map<DBConfig, DBBase> getDbMap() {
return Collections.unmodifiableMap(this.dbMap);
}

private static class InstanceHolder {
static final MYSQLManager instance_ = new MYSQLManager();

private InstanceHolder() {
}
}
}