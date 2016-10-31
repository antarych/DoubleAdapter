package antarych.app.doubleadapter.adapters;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import antarych.app.doubleadapter.R;
import antarych.app.doubleadapter.models.MessageModel;
import antarych.app.doubleadapter.models.MessengerObject;
import antarych.app.doubleadapter.models.NotificationModel;

/**
 * Created by Antarych on 04.10.2016.
 */

public class MessengerAdapter extends ArrayAdapter {
    ArrayList<MessengerObject> list;

    public MessengerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.list = (ArrayList<MessengerObject>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (list.get(position) instanceof MessageModel) {
            MessageViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_message, null);
                holder = new MessageViewHolder(
                        (TextView) convertView.findViewById(R.id.tv_message_text),
                        (TextView) convertView.findViewById(R.id.tv_sender_name),
                        (TextView) convertView.findViewById(R.id.tv_time)
                );

                convertView.setTag(holder);

            } else {
                holder = (MessageViewHolder) convertView.getTag();
            }

            MessageModel message = (MessageModel) list.get(position);
            holder.sender.setText(message.sender);
            holder.text.setText(message.text);
            holder.time.setText(message.time);

            convertView.setTag(holder);

        } else if (list.get(position) instanceof NotificationModel){
            NotificationViewHolder holder;

            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_notification, null);
                holder = new NotificationViewHolder(
                        (TextView) convertView.findViewById(R.id.tv_notification_text)
                );
            }else {
                holder = (NotificationViewHolder) convertView.getTag();
            }
            NotificationModel notification = (NotificationModel) list.get(position);
            holder.text.setText(notification.text);
        }
        return convertView;
    }

    class MessageViewHolder{
        TextView text;
        TextView sender;
        TextView time;

        public MessageViewHolder(TextView text, TextView sender, TextView time) {
            this.text = text;
            this.sender = sender;
            this.time = time;
        }
    }

    class NotificationViewHolder{
        TextView text;

        public NotificationViewHolder(TextView text) {
            this.text = text;
        }
    }
}
