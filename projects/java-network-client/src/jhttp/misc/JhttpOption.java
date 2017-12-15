// package jhttp;

/*
*
* command line options are used to specify :
*
* -url
* -http method
* -http headers
* -http body (urlencoded)
* -output verbosity
* -todo : http body for multipart form data
*
*/

public enum JhttpOption {
	URL, METHOD, HEADER, DATA, VERBOSE, UNKNOWN;
}
