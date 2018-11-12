from twilio.rest import Client


# Your Account Sid and Auth Token from twilio.com/console
account_sid = 'AC99411717a888563271a71cb2f4c3b0d9'
auth_token = 'my secret token'
client = Client(account_sid, auth_token)

message = client.messages.create(
                              from_='+19282956808',
                              body='I died',
                              to='+18605183270'
                          )

print(message.sid)
