/*      Copyright 2022 Mohammad Karan Arora

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Karan Arora on 20-11-2021
 */
@Getter
@Builder
public class UserData {

    private String name;
    private String job;

}