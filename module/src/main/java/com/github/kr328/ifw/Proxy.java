package com.github.kr328.ifw;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;

import com.github.kr328.magic.aidl.ServerProxy;
import com.github.kr328.magic.aidl.ServerProxyFactory;
import com.github.kr328.magic.aidl.TransactProxy;

import java.util.ArrayList;

public class Proxy extends IPackageManager.Stub {
    public static final ServerProxyFactory<IPackageManager, Proxy> FACTORY =
            ServerProxy.mustCreateFactory(IPackageManager.class, Proxy.class, false);

    private final IPackageManager original;

    public Proxy(final IPackageManager original) {
        this.original = original;
    }

    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentActivities(
            final Intent intent,
            final String resolvedType,
            final int flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentActivities(
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        int callingUid = Binder.getCallingUid();
        if (callingUid >= Process.FIRST_APPLICATION_UID && callingUid <= Process.LAST_APPLICATION_UID) {
            String[] packages = original.getPackagesForUid(callingUid);
            if (packages != null && packages.length > 0) {
                String callingPackage = packages[0];
                if (callingPackage != null)
                    if (callingPackage.equals("com.jakting.shareclean") || callingPackage.equals("com.jakting.shareclean.debug")) {
                        String action = intent.getAction();
                        String type = intent.getType();
                        if (action != null && action.equals(Intent.ACTION_PROCESS_TEXT)
                                && type != null && type.equals("text/tigerinthewall")) {
                            return new ParceledListSlice<>(new ArrayList<>());
                        }
                    }
            }
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.ACTIVITY,
                        intent,
                        resolvedType
                )
        );
    }

    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentActivities(
            final Intent intent,
            final String resolvedType,
            final long flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentActivities(
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        int callingUid = Binder.getCallingUid();
        if (callingUid >= Process.FIRST_APPLICATION_UID && callingUid <= Process.LAST_APPLICATION_UID) {
            String[] packages = original.getPackagesForUid(callingUid);
            if (packages != null && packages.length > 0) {
                String callingPackage = packages[0];
                if (callingPackage != null)
                    if (callingPackage.equals("com.jakting.shareclean") || callingPackage.equals("com.jakting.shareclean.debug")) {
                        String action = intent.getAction();
                        String type = intent.getType();
                        if (action != null && action.equals(Intent.ACTION_PROCESS_TEXT)
                                && type != null && type.equals("text/tigerinthewall")) {
                            return new ParceledListSlice<>(new ArrayList<>());
                        }
                    }
            }
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.ACTIVITY,
                        intent,
                        resolvedType
                )
        );
    }

    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentActivityOptions(
            final ComponentName caller,
            final Intent[] specifics,
            final String[] specificTypes,
            final Intent intent,
            final String resolvedType,
            final int flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentActivityOptions(
                caller,
                specifics,
                specificTypes,
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.ACTIVITY,
                        intent,
                        resolvedType
                )
        );
    }

    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentActivityOptions(
            final ComponentName caller,
            final Intent[] specifics,
            final String[] specificTypes,
            final Intent intent,
            final String resolvedType,
            final long flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentActivityOptions(
                caller,
                specifics,
                specificTypes,
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.ACTIVITY,
                        intent,
                        resolvedType
                )
        );
    }


    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentServices(
            final Intent intent,
            final String resolvedType,
            final int flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentServices(
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.SERVICE,
                        intent,
                        resolvedType
                )
        );
    }

    @Override
    @TransactProxy
    public ParceledListSlice<ResolveInfo> queryIntentServices(
            final Intent intent,
            final String resolvedType,
            final long flags,
            final int userId
    ) throws RemoteException {
        final ParceledListSlice<ResolveInfo> result = original.queryIntentServices(
                intent,
                resolvedType,
                flags,
                userId
        );

        if (Firewall.get() == null) {
            return result;
        }

        return new ParceledListSlice<>(
                Firewall.get().filterResult(
                        result.getList(),
                        Firewall.IntentFirewall.FilterType.SERVICE,
                        intent,
                        resolvedType
                )
        );
    }

    @Override
    @TransactProxy
    public String[] getPackagesForUid(int uid) throws RemoteException {
        return original.getPackagesForUid(uid);
    }
}
