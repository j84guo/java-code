// approach :
// 1. directed graph represents user as nodes, follows as list of edges
// 2. for a particular user and a set of tweets find the tweets that pass the threshhold (visit neighbours)
// 3. sort tweets 
static int[] getRecommendedTweets(int[][] followGraph_edges, int[][] likeGraph_edges, int targetUser, int minLikeThreshold) {
    
    //build a directed user graph
    HashMap<Integer, LinkedList<Integer>> userMap = buildUserGraph(followGraph_edges);
    if(!userMap.containsKey(targetUser)){
        return new int[0];
    }
    
    //get target user contacts
    LinkedList<Integer> contacts = userMap.get(targetUser);
 
    //read each tweet and check for minimum likes
    Tweet tweet;
    HashMap<Integer, Tweet> tweets = new HashMap<Integer, Tweet>();
    ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
    for(int[] like : likeGraph_edges){
        int user = like[0];
        int tweetId = like[1]; 
        
        if(!tweets.containsKey(tweetId)){
            tweet = new Tweet(tweetId, 0, 0);
            tweets.put(tweetId, tweet);
        }else{
            tweet = tweets.get(tweetId);
        }
        
        tweet.generalLikes++;
        for(int contactId : contacts){
            if(contactId == user){
                tweet.contactLikes++;
            }
        }
        
        if(tweet.contactLikes >= minLikeThreshold){
            tweetList.add(tweet);
        }
    }
    
    //sort by decreasing relevant likes, if necessary use general likes
    Collections.sort(tweetList, new Comparator<Tweet>(){
        public int compare(Tweet t1, Tweet t2){
            if(t2.contactLikes == t1.contactLikes){
                return t2.generalLikes - t1.generalLikes;
            }else{
                return t2.contactLikes - t1.contactLikes;
            }
        }
    });
    
    //copy to array
    int[] output = new int[tweetList.size()];
    for(int i=0; i<output.length; i++){
        output[i] = tweetList.get(i).tweetId;
    }
    return output;
}

//map users to their contacts with a directed graph
private static HashMap<Integer, LinkedList<Integer>> buildUserGraph(int[][] followGraph_edges){
 
    HashMap<Integer, LinkedList<Integer>> userMap = new HashMap<Integer, LinkedList<Integer>>();
    LinkedList<Integer> connections;
    for(int[] pair : followGraph_edges){
        int user = pair[0];
        int contact = pair[1];
        
        if(userMap.containsKey(user)){
            connections = userMap.get(user);
        }else{
            connections = new LinkedList<Integer>();
        }
        
        connections.add(contact);
        userMap.put(user, connections);
    }
    
    return userMap;
}

//encapsulates a tweet id and its relevant likes
static class Tweet {
    int tweetId;
    int contactLikes;
    int generalLikes;
    
    Tweet(int tweetId, int contactLikes, int generalLikes){
        this.tweetId = tweetId;
        this.contactLikes = contactLikes;
        this.generalLikes = generalLikes;
    }
}