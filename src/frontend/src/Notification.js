import {notification} from "antd";

const openNotificationWithIcon = (type, message, description) => {
  notification[type]({
    message,
    description
  });
};

export const successNotification = (message, description, placement) =>
    openNotificationWithIcon('success', message, description, placement);

export const errorNotification = (message, description, placement) =>
    openNotificationWithIcon('error', message, description);

export const infoNotification = (message, description, placement) =>
    openNotificationWithIcon('info', message, description);

export const warningNotification = (message, description, placement) =>
    openNotificationWithIcon('warning', message, description);