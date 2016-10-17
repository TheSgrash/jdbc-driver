package com.ibm.si.jaql.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ibm.si.jaql.rest.ArielDatabase;

/**
 * Factory for returning the Ariel Database
 *  
 * @author IBM
 *
 */
public class ArielFactory
{
	private static Map<String,IArielDatabase> dbCache = null;
	
	static
	{
		dbCache = new ConcurrentHashMap<String,IArielDatabase>();
	}
	
	public static IArielDatabase getArielDatabase(
		final String ip,
		final String user,
		final String password) throws ArielException
	{
		return getArielDatabase(ip,user,password,443);
	}
	public static IArielDatabase getArielDatabase(
			final String ip,
			final String user,
			final String password,
			final int port) throws ArielException
	{
    System.err.println("DB Cache: " + dbCache + ", " + ip);
		IArielDatabase result = dbCache.get(ip);
		if (result == null)
		{
			result = new ArielDatabase(ip, user, password,port);
			dbCache.put(ip, result);
		}
		
		return result;
	}
  public static IArielDatabase getArielDatabase(
    final String ip,
    final String auth_token) throws ArielException
  {
    return getArielDatabase(ip,auth_token,443);
  }
  public static IArielDatabase getArielDatabase(
    final String ip,
    final String auth_token,
    int port) throws ArielException
  {
    IArielDatabase result = dbCache.get(ip);
    if (result == null)
    {
      result = new ArielDatabase(ip, auth_token, port);
      dbCache.put(ip, result);
    }
    return result;
  }
}
