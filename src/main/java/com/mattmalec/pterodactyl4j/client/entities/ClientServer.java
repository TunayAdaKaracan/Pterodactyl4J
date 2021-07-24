package com.mattmalec.pterodactyl4j.client.entities;

import com.mattmalec.pterodactyl4j.PowerAction;
import com.mattmalec.pterodactyl4j.PteroAction;
import com.mattmalec.pterodactyl4j.client.managers.*;
import com.mattmalec.pterodactyl4j.entities.Server;
import com.mattmalec.pterodactyl4j.utils.Relationed;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ClientServer extends Server {

	boolean isServerOwner();
	long getInternalIdLong();
	default String getInternalId() { return Long.toUnsignedString(getInternalIdLong()); }
	SFTP getSFTPDetails();
	String getInvocation();
	Set<String> getEggFeatures();
	ClientEgg getEgg();
	String getNode();
	boolean isSuspended();
	boolean isInstalling();

	ClientServerManager getManager();

	PteroAction<Utilization> retrieveUtilization();
	PteroAction<Void> setPower(PowerAction powerAction);

	default PteroAction<Void> start() {
		return setPower(PowerAction.START);
	}

	default PteroAction<Void> stop() {
		return setPower(PowerAction.STOP);
	}

	default PteroAction<Void> restart() {
		return setPower(PowerAction.RESTART);
	}

	default PteroAction<Void> kill() {
		return setPower(PowerAction.KILL);
	}

	PteroAction<Void> sendCommand(String command);

	WebSocketBuilder getWebSocketBuilder();

	List<ClientSubuser> getSubusers();
	Relationed<ClientSubuser> getSubuser(UUID uuid);
	default Relationed<ClientSubuser> getSubuser(String uuid) {
		return getSubuser(UUID.fromString(uuid));
	}
	SubuserManager getSubuserManager();

	PteroAction<List<Backup>> retrieveBackups();
	PteroAction<Backup> retrieveBackup(UUID uuid);
	default PteroAction<Backup> retrieveBackup(String uuid) {
		return retrieveBackup(UUID.fromString(uuid));
	}
	BackupManager getBackupManager();

	PteroAction<List<Schedule>> retrieveSchedules();
	default PteroAction<Schedule> retrieveSchedule(long id) {
		return retrieveSchedule(Long.toUnsignedString(id));
	}
	PteroAction<Schedule> retrieveSchedule(String id);
	ScheduleManager getScheduleManager();

}
