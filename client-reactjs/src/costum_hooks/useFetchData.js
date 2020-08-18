import { useEffect, useState } from "react";
import { defaultInstance } from "../utils";

const log = (isLogActive) => (message) => {
  if (isLogActive) console.log(message);
};

const useFetchData = (url, isLogActive = false) => {
  const [error, setError] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [response, setResponse] = useState();

  useEffect(() => {
    const fetchInfo = async () => {
      try {
        const result = await defaultInstance.get(url);
        setIsLoading(true);
        log(isLogActive)(`useFetchData: loading information from url=${url}`);
        setResponse(result);
      } catch (ex) {
        setError(true);
        log(isLogActive)(`useFetchData: expection is rised with stack=${ex}`);
      }
    };
    fetchInfo();
  }, [url]);

  return [response, isLoading, error];
};
export default useFetchData;
