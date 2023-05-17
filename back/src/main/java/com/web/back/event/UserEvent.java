package com.web.back.event;

import com.web.back.domain.UserEventLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public class UserEvent extends ApplicationEvent {
    private final UserEventLog userEventLog;

    /**
     * Instantiates a new User event.
     *
     * @param userEventLog the user event log
     */
    public UserEvent(final UserEventLog userEventLog) {
        super(userEventLog);
        this.userEventLog = userEventLog;
    }

    /**
     * Gets user event log.
     *
     * @return the user event log
     */
    public UserEventLog getUserEventLog() {
        return userEventLog;
    }
}
